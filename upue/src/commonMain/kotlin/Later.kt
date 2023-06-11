package pl.mareklangiewicz.upue

// TODO_someday: maybe later add stuff like these below

/*

// maybe needed to easily implement multiplatform maps with reference equality semantics. just wrap keys in this class.
// sth similar to Java:
// https://developer.android.com/reference/java/util/IdentityHashMap
// https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/IdentityHashMap.html
// see also answers here:
// https://stackoverflow.com/questions/74525402/how-to-force-object-equivalence-for-keys-in-a-kotlin-hashmap

class IdentKey(private val obj: Any?) {
    override fun equals(other: Any?): Boolean = this === other
    override fun hashCode(): Int = obj?.hashCode() ?: 0
}


// C&P from treebrowser:UReflect.cmn.kt
// todo_someday_maybe: some form of this in public utilities somewhere?
internal fun <T> Collection<T>.limitIfNN(limit: Int?, log: (Any?) -> Unit = ::println) = when {
    limit == null -> this
    limit >= size -> this
    else -> {
        log("limiting ${size} to $limit")
        take(limit)
    }
}


// C&P from treebrowser:TreeSystem.kt

private interface WithId { val id: Int }

// todo_someday_maybe: put sth like this in public upue lib?
private class WrpCache<Src, Wrp: WithId>(private val wrap: (id: Int, Src) -> Wrp) {

    private val cacheMap = mapOf<Src, Int>() // value is index in arr
    private val cacheArr = arrayListOf<Pair<Src, Wrp>>() // each Wrp.id is index in arr

    fun getSrc(wrp: Wrp?) = if (wrp == null) null else cacheArr[wrp.id].first

    operator fun get(src: Src?): Wrp? {
        val srcn = src ?: return null
        cacheMap[srcn]?.let { return cacheArr[it].second }
        val wrpn = wrap(cacheArr.size, srcn)
        cacheArr.add(srcn to wrpn)
        return wrpn
    }
}


 */
