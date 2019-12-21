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
        "type": "Increase",
        "labels": ["INC"],
        "bgColor": "lightgreen",
        "borderColor": "darken",
        "arcs": [
            {"type": "theme", "labels": ["theme"], "borderColor": "darken", "bgColor":"violet"},
            {"type": "quantifier", "labels": ["quant"], "borderColor": "darken", "bgColor":"violet"}
        ]
      },

      {
        "type": "Decrease",
        "labels": ["DEC"],
        "bgColor": "red",
        "borderColor": "darken",
        "arcs": [
            {"type": "theme", "labels": ["theme"], "borderColor": "darken", "bgColor":"violet"},
            {"type": "quantifier", "labels": ["quant"], "borderColor": "darken", "bgColor":"violet"}
        ]
      },

      {
        "type": "Causal",
        "labels": ["CAUSAL"],
        "bgColor": causalEventColor,
        "borderColor": "darken",
        "arcs": [
          {"type": "cause", "labels": ["cause"], "borderColor": "darken", "bgColor":"pink"},
          {"type": "effect", "labels": ["effect"], "borderColor": "darken", "bgColor":"pink"}
         ]
      },

      {
        "type": "Correlation",
        "labels": ["CORRELATION"],
        "bgColor": correlationEventColor,
        "borderColor": "darken",
        "arcs": [
          {"type": "cause", "labels": ["cause"], "borderColor": "darken", "bgColor":"pink"},
          {"type": "effect", "labels": ["effect"], "borderColor": "darken", "bgColor":"pink"}
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

        // stop the form from submitting the normal way and refreshing the page
        event.preventDefault();

        // collect form data
        var formData = {
            'sent': $('textarea[name=text]').val()
        }

        if (!formData.sent.trim()) {
            alert("Please write something.");
            return;
        }

        // show spinner
        document.getElementById("overlay").style.display = "block";

        // process the form
        $.ajax({
            type: 'GET',
            url: 'parseSentence',
            data: formData,
            dataType: 'json',
            encode: true
        })
        .fail(function () {
            // hide spinner
            document.getElementById("overlay").style.display = "none";
            alert("error");
        })
        .done(function (data) {
            console.log(data);
            syntaxLiveDispatcher.post('requestRenderData', [$.extend({}, data.syntax)]);
            eidosMentionsLiveDispatcher.post('requestRenderData', [$.extend({}, data.eidosMentions)]);
            document.getElementById("groundedAdj").innerHTML = data.groundedAdj;
            document.getElementById("parse").innerHTML = data.parse;
            // hide spinner
            document.getElementById("overlay").style.display = "none";
        });

    });
});
