interface BuildType {
    companion object {
        const val RELEASE = "release"
        const val DEBUG = "debug"
    }

    val isDebuggable : Boolean
    val isMinifyEnabled : Boolean
    val isTestCoverageEnabled : Boolean
}


object BuildTypeProd : BuildType {
    override val isDebuggable: Boolean
        get() = false
    override val isMinifyEnabled: Boolean
        get() = false
    override val isTestCoverageEnabled: Boolean
        get() = false
}