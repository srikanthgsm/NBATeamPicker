package com.nbateampicker.data.entity

/*
{
        "id": 1,
		"abbreviation": "ATL",
		"city": "Atlanta",
		"conference": "East",
		"division": "Southeast",
		"full_name": "Atlanta Hawks",
		"name": "Hawks"
}
 */
data class TeamDetail(
    val id: Int?,
    val abbreviation: String?,
    val city: String?,
    val conference: String?,
    val division: String?,
    val full_name: String?,
    val name: String?
)