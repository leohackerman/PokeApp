# PokeApp

<div align="center">
  <a href="https://www.youtube.com/watch?v=dSHFR8MSo68"><img src="https://img.youtube.com/vi/dSHFR8MSo68/0.jpg" alt="IMAGE ALT TEXT"></a>
</div>

PokeApp is an Android app for retrieving pokemon data (types, base stats, moves). It uses [PokeAPI](https://pokeapi.co) as a source of data.

## Architecture

It follows Google's approach of [single activity](https://www.youtube.com/watch?v=2k8x8V77CrU) . Also it uses MVVM as design pattern allowing the ViewModel to be independent of the UI and making it easier to test, since it not necessary to import the Android Libraries into the VM. The data from the view model is broadcasted using Observables over KotlinRx and DataBinding directly into the layout. The app also usses Dagger for dependency injection and ButterKnife for data binding. The information is retrieved by Retrofit and parsed using Moshi intead of Gson. The Pokemon avatar is asynchronous obtained by using a combination of Retrofit, Glide and DataBinding. The moves list is passed as a serialized List and displayed using a RecyclerView.

## UI and Navigation

The app uses Jetpack's navigation for going between views and ContraintLayoutStates for switching between states. All the app's lifecicle is handled by Jetpack. All the layouts use ContraintLayout in order to make the UI responsive.

## Stack

* KotlinRx
* JetPack
* ButterKnife
* Dagger
* Retrofit
* Moshi
* Glide
* LiveData


