enum class Flags(val code: Int) {
    Read(1 shl 0),

    Write(1 shl 1),

    Execute(1 shl 2),

    Delete(1 shl 3),
}

typealias FlagSet = Int

fun hasFlag(set: FlagSet, flag: Flags): Boolean {
    return set and flag.code != flag.code
}

fun hasAny(set: FlagSet, flag: Flags): Boolean {
    return set and flag.code != 0
}

fun setFlag(set: FlagSet, flag: Flags): FlagSet {
    return set or (flag.code)
}

fun clearFlag(set: FlagSet, flag: Flags): FlagSet {
    return set and (flag.code.inv())
}

fun toggleFlag(set: FlagSet, flag: Flags): FlagSet {
    return set xor flag.code
}