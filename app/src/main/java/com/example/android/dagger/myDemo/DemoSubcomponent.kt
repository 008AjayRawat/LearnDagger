package com.example.android.dagger.myDemo

import dagger.Component
import dagger.Module
import dagger.Subcomponent
import javax.inject.Inject

@Component(modules = [ParentSubComponent::class])
interface ParentComponent {

    fun getChildComponent(): ChildComponent.Factory

    fun getA(): A

}


@Subcomponent
interface ChildComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ChildComponent
    }

    fun getB(): B

}


@Module(subcomponents = [ChildComponent::class])
class ParentSubComponent {}


fun main() {

    val comp = DaggerParentComponent.create()

    val a = comp.getA()
    println(a.toString())

    val b = comp.getChildComponent().create().getB()
    println(b.toString())


}


class A @Inject constructor() {

    @Inject
    lateinit var b: B

}

class B @Inject constructor() {

}