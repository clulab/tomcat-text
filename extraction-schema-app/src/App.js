import React, { useState, useEffect } from 'react';
import './App.css';

function App() {

  const [data, setData] = useState(null);

  useEffect(() => {
    fetch('http://localhost:9000')
      .then(response => response.json())
      .then(data => {
        setData(data)
      })
      .catch(error => console.error(error));

    function handleClick(event) {
      if (event.target.classList.contains('parent')) {
        event.target.classList.toggle('active');
        const content = event.target.nextElementSibling;
        content.style.display = content.style.display === 'block' ? 'none' : 'block';
      }
    }
    document.addEventListener('click', handleClick);
    return () => {
      document.removeEventListener('click', handleClick);
    };
  }, []);


  function updateTree(tree_dict, names, args, keep) {
    if (!names.length) {
      tree_dict['Bool_for_bracket'] = keep;
      if (args.length) {
        tree_dict["arguments"] = args;
      }
      return;
    }
    if (!tree_dict[names[0]]) {
      tree_dict[names[0]] = {};
    }
    updateTree(tree_dict[names[0]], names.slice(1), args, keep);
  }

  function writeTreeDictHtml(tree_dict, level) {
    let html_code = "";

    for (let key in tree_dict) {
      if (key === "Bool_for_bracket") {
        continue;
      } else if (key === "arguments") {
        const args = tree_dict['arguments'];
        html_code += '<ul style="list-style-type:disc;">';
        for (let arg in args) {
          const argument_labels = args[arg]['label(s)'].map(x => `<span class="label">${x}</span>`).join('|');
          const argument_name = `<span class="argument-name">${arg}</span>`;
          html_code += `<li> ${argument_name} (${argument_labels})` +
            ` (${args[arg]['quantifier(s)']})`.replace(
              "`", ""
            ).replace("_none_", "") +
            '</li>\n';
        }
        html_code += "</ul>";
      } else if ('Bool_for_bracket' in tree_dict[key]) {
        const label = (tree_dict[key]['Bool_for_bracket'] === true) ? key : `[${key}]`;
        //check if this is a parent or not
        if (Object.keys(tree_dict[key]).length === 1) {
          html_code += `<li><span class="label">${label}</span></li>\n`;
        }
        //if a parent
        else {
          html_code += `
            <button type="button" class="parent label">${label}</button>
            <div class="content">\n
            `;
          html_code += writeTreeDictHtml(tree_dict[key], level + 1);
          html_code += '</div>\n';
        }
      } else {
        if (Object.keys(tree_dict[key]).length === 1) {
          html_code += `<li>${key}</li>\n`;
        }
        //if a parent
        else {
          html_code += `<button type="button" class="parent label">${key}</button>\n`;
          html_code += '<div class="content">\n';
          html_code += writeTreeDictHtml(tree_dict[key], level + 1);
          html_code += '</div>\n';
        }
      }
    }
    return html_code
  }

  let treeDict = {}
  let level = 0


  if (data) {
    let args;
    for (let extraction of data) {
      let names = extraction["labels"].slice().reverse();
      const keep = extraction["keep"].some(Boolean);
      if (extraction["argumentsPerRule"]) {
        args = {};
        for (let rule_argument of extraction["argumentsPerRule"]) {
          for (let arg of rule_argument) {
            let required = arg["required"];
            let arg_str = required ? arg["name"] : `[${arg['name']}]`;
            if (!(arg_str in args)) {
              args[arg_str] = {
                "label(s)": new Set([arg["label"]]),
                "quantifier(s)": arg["quantifier"],
              };
            } else {
              args[arg_str]["label(s)"].add(arg["label"]);
            }
          }
        }
      } else {
        args = [];
      }
      updateTree(treeDict, names, args, keep);
    }

  }

  return (
    <div>
      {data ? (
        <>
          <div className='navigation-container'>
            <div className='top-container'>
              <div className='asist'>ASIST</div>
              <div className='hyperlink-container'>
                <div>
                  <a href='https://ml4ai.github.io/tomcat/' style={{ color: 'white', textDecoration: 'none' }}>ToMCAT</a>
                </div>
                <div>
                  <a href='https://github.com/clulab/tomcat-text' style={{ color: 'white', textDecoration: 'none' }}>Github</a>
                </div>
              </div>
            </div>
            <h1>ASIST Extraction Schemas</h1>
          </div>
          <div class='lower-container'>


            <div className='basic-info-container'>
              <div>Authors: Adarsh Pyarelal, Rebecca Sharp</div>
              <div>Agent version: """ + agent_version + """</div>
              <div>Document generation timestamp (UTC): """ + str(date) + """</div>
              <div>This is automatically generated documentation of the different entities and
                events being extracted by the University of Arizona <a href='https://github.com/clulab/tomcat-text'>Dialog Agent</a></div>
            </div>
            <h2> Format </h2>
            <p>
              The extractions are listed below.
            </p>
            <p>
              The full path to the leaf node in the taxonomy is represented in a hierarchical
              structure, with each level indented from a new line. If the extraction is only
              output as an argument of another extraction, rather than as a standalone
              extraction, the name of the extraction is wrapped in square brackets ([]).
            </p>
            <p>
              The argument of an extraction, if any, is listed below the extraction.  If the
              the argument is optional, the name is enclosed in square brackets, it is an
              optional argument. The possible labels that can be assigned to the argument
              come next, enclosed in parentheses and separated by the | character.
              Finally, the third component of the argument line is a list of quantifiers,
              separated by commas, and enclosed in
              parentheses.
            </p>
            <p>
              <a href='https://github.com/clulab/processors'>Odin</a> arguments can be quantified in
              several ways. First, they may be optional (<span className='special-character'>?</span>), meaning that if they can be
              added, given a particular sentence, they will be, but if not the Mention can
              still be found. Further, they can be allowed to match multiple arguments. For
              example, in the sentence <i>"Ferdinand ate ice cream and pop tarts."</i>", if you made a
              rule to extract eating events and used a <span className='special-character'>+</span> quantifier with the food argument
              <span className='special-character'>(food: Food+ = ...)</span>, then the Mention would have two separate arguments (ice
              cream and pop tarts). The quantifiers you may see are:
            </p>
            <p>
              <ul style={{ listStyleType: 'none' }}>
                <li> <span className='special-character'>?</span> (arg is optional)</li>
                <li> <span className='special-character'>+</span> (you need at least one, can have as many as are there)</li>
                <li> <span className='special-character'>(_min_, _max_)</span> (you need at least min and can have up to max, where min can be 0).</li>
              </ul>
            </p>
            <p>
              Take for example the following block:
              <ul style={{ listStyleType: 'none' }}>
                <li><span className="label">Concept</span></li>
                <li style={{ marginLeft: '15px' }}>  <span className="label">LeafNode</span>
                  <li style={{ marginLeft: '25px' }}>
                    <span className='argument-name' >arg1</span> (<span className="label">LeafNode1</span>|<span className="label">LeafNode2</span>) () </li>
                  <li style={{ marginLeft: '25px' }}> <span className='argument-name' >[arg2]</span> (<span className="label">LeafNode3</span>) (?) </li>
                </li>
              </ul>
              <p>
                The full path to the leaf node in the taxonomy is <span className='special-character'>Concept/LeafNode</span>.
                The extraction has two arguments,
                <span className='special-character'>arg1</span> and <span className='special-character'>arg2</span>,
                with <span className='special-character'>arg2</span> being optional.
                The <span className='special-character'>arg1</span> argument can be labeled as either
                <span className='special-character'>LeafNode1</span> or <span className='special-character'>LeafNode2</span> (we
                elide the full taxonomy paths here for clarity). Additionally, <span className='special-character'>arg2</span> has an
                optional quantifier.

                The extractions are documented in the following section.
              </p>
            </p>
            <h2>Extractions</h2>
            <p style={{ marginBottom: '40px' }}>Note: '+' denotes the presence of child nodes; '-' indicates that the node is a leaf node with no child nodes.</p>
            <div dangerouslySetInnerHTML={{ __html: writeTreeDictHtml(treeDict, level) }} />
          </div>
        </>
      ) : (
        <div>Loading extraction schemas...</div>
      )
      }
    </div >

  );
}

export default App;
