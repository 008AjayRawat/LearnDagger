package com.example.android.dagger.myDemo

import dagger.Component
import javax.inject.Inject

@Component
interface MainComponent {

    fun getMainDemo(): MainDemo

}


@Component(dependencies = [MainComponent::class])
interface DependentComponent {

    @Component.Factory
    interface Factory {
        fun create(mainComponent: MainComponent): DependentComponent
    }

    fun inject(mainA: MainA)


}

fun main() {

    val mainCom = DaggerMainComponent.create()
    val depComp = DaggerDependentComponent.factory().create(mainCom)

    val mainDemo = mainCom.getMainDemo()
    depComp.inject(mainDemo.mainA)

    mainDemo.mainA.showMainB()




}

class MainDemo @Inject constructor() {

    @Inject
    lateinit var mainA: MainA

    fun showMainA() {
        println(mainA.toString())
    }


}


class MainA @Inject constructor() {

    @Inject
    lateinit var mainB: MainB

    fun showMainB() {
        println(mainB.toString())
    }
}


class MainB @Inject constructor() {
}