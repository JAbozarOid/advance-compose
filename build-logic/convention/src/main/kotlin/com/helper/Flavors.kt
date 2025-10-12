package com.helper


enum class Flavors(val value: String, val versionNameSuffix: String) {

    BAZAAR_MARKET("Bazaar", "-bazaar"),
    GOOGLE_PLAY_MARKET("GooglePlay", "-googlePlay"),
    MYKET_MARKET("Myket", "-myket"),

    PRODUCTION_HOST("production","-production"),
    DEVELOPMENT_HOST("development","-development"),
    STAGE_HOST("stage","-stage"),
}