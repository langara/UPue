package pl.mareklangiewicz.pue

/**
 * Created by Marek Langiewicz on 21.02.16.
 *
 * Warning: It's mostly an exercise and contains a lot of HACKY (unreadable) syntax.
 * Do not use it (or even "conventions" like this) in production - unless you are evil ;)
 *
 * TODO LATER: consider making some functions inline (see Pue.kt:44 for details)
 */

fun <T> ident(t: T): T = t

fun <T> const(t: T) = { _: Unit -> t }

/** function compositions: (f * g)(x) = g(f(x)) (works also for objects with 'invoke' method */
operator infix fun <A, B> Function0<A>.times(f: Function1<A, B>): Function0<B> = { f(this()) }

operator infix fun <A, B, C> Function1<A, B>.times(f: Function1<B, C>): Function1<A, C> = { f(this(it)) }

operator infix fun <A, B, C, D> Function2<A, B, C>.times(f: Function1<C, D>): Function2<A, B, D> = { a, b -> f(this(a, b)) }

operator infix fun <A, B, C, D, E> Function3<A, B, C, D>.times(f: Function1<D, E>): Function3<A, B, C, E> = { a, b, c -> f(this(a, b, c)) }

/** a "pipe" */
operator infix fun <A, B> A.div(f: Function1<A, B>): B = f(this)

/** It just perform additional given action on any function result. Here U is usually Unit. It is ignored anyway */
operator infix fun <A, U> Function0<A>.rem(f: Function1<A, U>): Function0<A>
        = { val a = this(); f(a); a }

operator infix fun <A, B, U> Function1<A, B>.rem(f: Function1<B, U>): Function1<A, B>
        = { val b = this(it); f(b); b }

operator infix fun <A, B, C, U> Function2<A, B, C>.rem(f: Function1<C, U>): Function2<A, B, C>
        = { a, b -> val c = this(a, b); f(c); c }

operator infix fun <A, B, C, D, U> Function3<A, B, C, D>.rem(f: Function1<D, U>): Function3<A, B, C, D>
        = { a, b, c -> val d = this(a, b, c); f(d); d }


fun <A, B, R>       Function2<A, B, R>      .curry() = { a: A -> { b: B ->                       this(a, b)             } }
fun <A, B, C, R>    Function3<A, B, C, R>   .curry() = { a: A -> { b: B -> { c: C -> {           this(a, b, c)      } } } }
fun <A, B, C, D, R> Function4<A, B, C, D, R>.curry() = { a: A -> { b: B -> { c: C -> { d: D -> { this(a, b, c, d) } } } } }

fun <T> lazy(initializer: (Unit) -> T) = kotlin.lazy { initializer(Unit) }

fun <R> memoize(function: () -> R): () -> R {
    val r by lazy(function)
    return { r }
}

fun <R> memoize(function: (Unit) -> R): (Unit) -> R {
    val r by lazy(function)
    return { r }
}

// TODO LATER: przejrzec funkcyjne biblioteki kotlinowe w necie na szybko i moze cos tu jeszcze wrzucic
// ale jestesmy minimalistyczni i nie chcemy za duzo - jak cos mozna latwo recznie

/** Crazy shortcut for doing something a few times... Here U is usually Unit. It is ignored anyway */
operator fun <U> Int.invoke(f: Function1<Unit, U>) = (1..this).forEach { f(Unit) }

// TODO NOW: dodac gdzie sie da in/out przy zmiennych typowych!

/**
 * crazy "ternary" conditional operator :-)
 * Use it like this:
 * val t = someBoolean % someTForTrue ?: someTForFalse
 * You can also do just:
 * someBoolean % doSomethingIfTrue()
 */
@Deprecated( "It always computes its argument (and it is confusing).", ReplaceWith("if (this == true) yes else null"))
operator fun <T> Boolean?.rem(yes: T): T? = if(this == true) yes else null

