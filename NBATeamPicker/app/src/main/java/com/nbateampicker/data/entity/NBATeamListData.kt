package com.nbateampicker.data.entity

/*
{
    "data": [{
		"id": 1,
		"abbreviation": "ATL",
		"city": "Atlanta",
		"conference": "East",
		"division": "Southeast",
		"full_name": "Atlanta Hawks",
		"name": "Hawks"
	}
}
*/
data class NBATeamListData(
    val data: MutableList<TeamDetail>
)
