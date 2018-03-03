package api.openevent

class OpenEvent {

    private object Holder { val INSTANCE = OpenEvent() }

    companion object {
        val instance: OpenEvent by lazy { Holder.INSTANCE }
    }

}
