package org.clulab.asist.text

import org.clulab.asist.BaseTest

class TestLocation extends BaseTest {

  behavior of "locations.yml"

  passingTest should "Find location words in the text" in {
    val text = "First, head North, then progress to the first zone on the South-East. " +
    "What does the map say about the South-West? " +
    "Go North-East, clear the area. " +
    "Can you head to the first floor. " +
    "Three victims in the back room. " +
    "Keep going till you are out of the first part. " +
    "Move into the empty space"


    val mentions = extractor.extractFromText(text)

    val firstfloor = DesiredMention("Infrastructure", "first floor")
    val backroom = DesiredMention("Infrastructure", "back room")

    val north_mention = DesiredMention("North", "North")
    val east_mention = DesiredMention("East", "East")
    val south_mention = DesiredMention("South", "South")
    val west_mention = DesiredMention("West", "West")

    val ne1_mention = DesiredMention("NorthEast", "North-East")
    val ne2_mention = DesiredMention("NorthEast", "North East")
    val ne3_mention = DesiredMention("NorthEast", "north-east")

    val nw1_mention = DesiredMention("NorthWest", "North-West")
    val nw2_mention = DesiredMention("NorthWest", "North West")
    val nw3_mention = DesiredMention("NorthWest", "north-west")

    val se1_mention = DesiredMention("SouthEast", "South-East")
    val se2_mention = DesiredMention("SouthEast", "South East")
    val se3_mention = DesiredMention("SouthEast", "south-east")

    val sw1_mention = DesiredMention("SouthWest", "South-West")
    val sw2_mention = DesiredMention("SouthWest", "South West")
    val sw3_mention = DesiredMention("SouthWest", "south-west")

    val zone_mention = DesiredMention("Zone", "zone")
    val location_mention = DesiredMention("Location", "space")

    testMention(mentions, north_mention)
    testMention(mentions, east_mention)
    testMention(mentions, zone_mention)
  }
}
