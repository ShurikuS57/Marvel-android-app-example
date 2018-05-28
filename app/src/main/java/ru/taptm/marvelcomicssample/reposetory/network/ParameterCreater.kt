package ru.taptm.marvelcomicssample.reposetory.network

class ParameterCreater {
    companion object {
        fun createComicsParams(offset:Int): HashMap<String, String> {
            val map = HashMap<String, String>()
            map[Params.ORDER_BY] = "modified"
            map[Params.HAS_DIGITAL_ISSUE] = "true"
            map[Params.OFFSET] = offset.toString()
            map[Params.FORMAT] = "digital comic"
            return map
        }
    }
}