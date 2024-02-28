package com.helper



enum class BuildTypes(val value: String, val versionNameSuffix: String, val isDebuggable: Boolean) {
    DEBUG("debug", "-debug", true),
    RELEASE("release", "-release", false)
}