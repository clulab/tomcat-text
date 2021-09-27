var bratLocation = 'assets/brat';

// Color names used
var baseNounPhraseColor = '#CCD1D1';
var increaseNounPhraseColor = '#BBDC90';
var decreaseNounPhraseColor = '#FC5C38';
var quantifierColor = '#AED6F1';
var quantifiedNounPhraseColor = '#85C1E9';
// fixme: whatever events we have in this project...
var causalEventColor = '#BB8FCE';
var correlationEventColor = '#F7DC6F';


head.js(
    // External libraries
    bratLocation + '/client/lib/jquery.min.js',
    bratLocation + '/client/lib/jquery.svg.min.js',
    bratLocation + '/client/lib/jquery.svgdom.min.js',

    // brat helper modules
    bratLocation + '/client/src/configuration.js',
    bratLocation + '/client/src/util.js',
    bratLocation + '/client/src/annotation_log.js',
    bratLocation + '/client/lib/webfont.js',

    // brat modules
    bratLocation + '/client/src/dispatcher.js',
    bratLocation + '/client/src/url_monitor.js',
    bratLocation + '/client/src/visualizer.js'
);

var webFontURLs = [
    bratLocation + '/static/fonts/Astloch-Bold.ttf',
    bratLocation + '/static/fonts/PT_Sans-Caption-Web-Regular.ttf',
    bratLocation + '/static/fonts/Liberation_Sans-Regular.ttf'
];

var collData = {
    entity_types: [ {
        "type"   : "Quantifier",
        "labels" : ["Quantifier", "Quant"],
        // Blue is a nice colour for a person?
        "bgColor": quantifierColor,
        // Use a slightly darker version of the bgColor for the border
        "borderColor": "darken"
    },
    {
            "type"   : "NounPhrase",
            "labels" : ["NounPhrase", "NP"],
            // Blue is a nice colour for a person?
            //"bgColor": "thistle",
            "bgColor": baseNounPhraseColor,
            // Use a slightly darker version of the bgColor for the border
            "borderColor": "darken"
        },
        {
            "type"   : "NounPhrase-Inc",
            "labels" : ["NounPhrase", "NP"],
            // Blue is a nice colour for a person?
            //"bgColor": "thistle",
            "bgColor": increaseNounPhraseColor,
            // Use a slightly darker version of the bgColor for the border
            "borderColor": "darken"
        },
        {
            "type"   : "NounPhrase-Dec",
            "labels" : ["NounPhrase", "NP"],
            // Blue is a nice colour for a person?
            //"bgColor": "thistle",
            "bgColor": decreaseNounPhraseColor,
            // Use a slightly darker version of the bgColor for the border
            "borderColor": "darken"
        },
        {
            "type"   : "NounPhrase-Quant",
            "labels" : ["NounPhrase", "NP"],
            // Blue is a nice colour for a person?
            //"bgColor": "thistle",
            "bgColor": quantifiedNounPhraseColor,
            // Use a slightly darker version of the bgColor for the border
            "borderColor": "darken"
        },
     ],

    event_types: [
      {
        "type": "Rubble",
        "labels": ["Rubble"],
        "bgColor": "lightgreen",
        "borderColor": "darken",
        "arcs": []
      },
      {
          "type": "Infrastructure",
          "labels": ["Infrastructure"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": []
      },
      {
          "type": "Medic",
          "labels": ["Medic"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": []
      },
      {
          "type": "Searcher",
          "labels": ["Searcher"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": []
      },
      {
          "type": "Engineer",
          "labels": ["Engineer"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": []
      },
      {
          "type": "Deictic",
          "labels": ["Deictic"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": []
      },
      {
          "type": "Victim",
          "labels": ["Victim"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": []
      },
      {
          "type": "Hammer",
          "labels": ["Hammer"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": []
      },
      {
          "type": "Alpha",
          "labels": ["Alpha"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": []
       },
      {
          "type": "Bravo",
          "labels": ["Bravo"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": []
       },
      {
          "type": "Delta",
          "labels": ["Delta"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": []
       },
      {
          "type": "Green",
          "labels": ["Green"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": []
       },
       {
         "type": "Yellow",
         "labels": ["Yellow"],
         "bgColor": "lightgreen",
         "borderColor": "darken",
         "arcs": []
      },
      {
           "type": "Question",
           "labels": ["Question"],
           "bgColor": "lightgreen",
           "borderColor": "darken",
           "arcs": []
       },
       {
          "type": "RoleSwitch",
          "labels": ["RoleSwitch"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": []
      },
      {
          "type": "Agreement",
          "labels": ["Agree"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": []
      },
      {
        "type": "Anaphor",
        "labels": ["Anaphor"],
        "bgColor": "lightgreen",
        "borderColor": "darken",
        "arcs": []
      },
      {
          "type": "Disagreement",
          "labels": ["Disagree"],
          "bgColor": "lightgreen",
          "borderColor": "darken",
          "arcs": [
            {"type": "target", "labels": ["target"], "borderColor": "darken", "bgColor":"violet"}
          ]
      },
      {
          "type": "Clear",
          "labels": ["Clear"],
          "bgColor": "lightblue",
          "borderColor": "darken",
          "arcs": [
            {"type": "target", "labels": ["target"], "borderColor": "darken", "bgColor":"violet"}
          ]
      },
      {
          "type": "ChangePriority",
          "labels": ["Change"],
          "bgColor": "lightblue",
          "borderColor": "darken",
          "arcs": [
            {"type": "target", "labels": ["target"], "borderColor": "darken", "bgColor":"violet"},
            {"type": "person", "labels": ["person"], "borderColor": "darken", "bgColor":"violet"}
          ]
        },
        {
          "type": "Commit",
          "labels": ["Commit"],
          "bgColor": "lightblue",
          "borderColor": "darken",
          "arcs": [
            {"type": "target", "labels": ["target"], "borderColor": "darken", "bgColor":"violet"}
          ]
        },
        {
          "type": "Continue",
          "labels": ["Continue"],
          "bgColor": "lightblue",
          "borderColor": "darken",
          "arcs": [
            {"type": "person", "labels": ["person"], "borderColor": "darken", "bgColor":"violet"}
          ]
        },
        {
          "type": "Sight",
          "labels": ["Sight"],
          "bgColor": "lightblue",
          "borderColor": "darken",
          "arcs": [
            {"type": "target", "labels": ["target"], "borderColor": "darken", "bgColor":"violet"}
          ]
        },
        {
          "type": "Move",
          "labels": ["Move"],
          "bgColor": "lightblue",
          "borderColor": "darken",
          "arcs": [
            {"type": "person", "labels": ["person"], "borderColor": "darken", "bgColor":"violet"},
            {"type": "target", "labels": ["target"], "borderColor": "darken", "bgColor":"violet"}
          ]
        },
        {
          "type": "Search",
          "labels": ["Search"],
          "bgColor": "lightblue",
          "borderColor": "darken",
          "arcs": [
            {"type": "area", "labels": ["area"], "borderColor": "darken", "bgColor":"violet"},
            {"type": "target", "labels": ["target"], "borderColor": "darken", "bgColor":"violet"}
          ]
        },
        {
          "type": "Save",
          "labels": ["Save"],
          "bgColor": "lightblue",
          "borderColor": "darken",
          "arcs": [
            {"type": "location", "labels": ["location"], "borderColor": "darken", "bgColor":"violet"},
            {"type": "target", "labels": ["target"], "borderColor": "darken", "bgColor":"violet"}
          ]
        },
        {
          "type": "Precedence",
          "labels": ["Precedence"],
          "bgColor": "lightyellow",
          "borderColor": "darken",
          "arcs": [
            {"type": "first_action", "labels": ["first_action"], "borderColor": "darken", "bgColor":"violet"},
            {"type": "second_action", "labels": ["second_action"], "borderColor": "darken", "bgColor":"violet"}
          ]
        }
    ]
};

// docData is initially empty.
var docData = {};

head.ready(function() {

    var syntaxLiveDispatcher = Util.embed('syntax',
        $.extend({'collection': null}, collData),
        $.extend({}, docData),
        webFontURLs
    );
    var eidosMentionsLiveDispatcher = Util.embed('eidosMentions',
        $.extend({'collection': null}, collData),
        $.extend({}, docData),
        webFontURLs
    );

    $('form').submit(function (event) {

        // Stop the form from submitting the normal way and refreshing the page
        event.preventDefault();

        // Sollect form data
        var formData = {
            'sent': $('textarea[name=text]').val()
        }

        if (!formData.sent.trim()) {
            alert("Please write something.");
            return;
        }

        // Show spinner
        document.getElementById("overlay").style.display = "block";

        // Process the form
        $.ajax({
            type: 'GET',
            url: 'parseSentence',
            data: formData,
            dataType: 'json',
            encode: true
        })
        .fail(function () {
            // Hide spinner
            document.getElementById("overlay").style.display = "none";
            alert("error");
        })
        .done(function (data) {
            console.log(data);
            syntaxLiveDispatcher.post('requestRenderData', [$.extend({}, data.syntax)]);
            eidosMentionsLiveDispatcher.post('requestRenderData', [$.extend({}, data.eidosMentions)]);
            document.getElementById("groundedAdj").innerHTML = data.mentionDetails;
            document.getElementById("parse").innerHTML = data.parse;

            // Hide spinner
            document.getElementById("overlay").style.display = "none";
        });

    });
});
