package com.helper


enum class Dimensions(val value: String, val flavors: List<Flavors>) {
    MARKET(
        "market",
        listOf(Flavors.BAZAAR_MARKET, Flavors.GOOGLE_PLAY_MARKET, Flavors.MYKET_MARKET)
    ),
    HOST(
        "host",
        listOf(Flavors.DEVELOPMENT_HOST, Flavors.PRODUCTION_HOST, Flavors.STAGE_HOST)
    )
}

