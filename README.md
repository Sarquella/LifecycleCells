Lifecycle Cells
===============

An Android library that provides a *[Lifecycle](https://developer.android.com/reference/android/arch/lifecycle/Lifecycle)* to any *[ViewHolder](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.ViewHolder)* through the implementation of the *[LifecycleOwner](https://developer.android.com/reference/android/arch/lifecycle/LifecycleOwner)* interface, allowing it to interact with a *[Lifecycle-Aware Component](https://developer.android.com/topic/libraries/architecture/lifecycle#implementing-lco)*.

#### 100% Kotlin ❤️
[![Maven Central](https://img.shields.io/maven-central/v/dev.sarquella.lifecyclecells/lifecyclecells)](https://search.maven.org/artifact/dev.sarquella.lifecyclecells/lifecyclecells) [![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15) [![License](https://img.shields.io/badge/license-Apache%202.0-lightgrey.svg)](https://opensource.org/licenses/Apache-2.0)

### Follow
<a href='https://ko-fi.com/S6S8RENM' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi1.png?v=2' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>

[![GitHub Follow](https://img.shields.io/github/followers/Sarquella.svg?label=Follow&style=social)](https://github.com/Sarquella) [![Twitter Follow](https://img.shields.io/twitter/follow/AdriSarquella.svg?label=Follow&style=social)](https://twitter.com/AdriSarquella)

## Table of Content
* [Motivation](#motivation)
* [Download](#download)
* [Sample Project](#sample-project)
* [Usage](#usage)
* [Observing LiveData](#observing-livedata)
* [License](#license)

## Motivation
During the *Google I/O 2017*, a new set of libraries where introduced under the name of *[Architecture Components](https://developer.android.com/topic/libraries/architecture/)*.
Among those various libraries, one of the most remarkables was the introduction of *[LiveData](https://developer.android.com/reference/android/arch/lifecycle/LiveData)*, an observable data holder class.
In order to be able to observe it, a class implementing the *LifecycleOwner* interface must be provided.

*Google* just provided this functionality to *Fragments* and *Activities* out of the box since *Support Library 26.1.0*.

This library pretends to provide *ViewHolder* with these capabilities, giving to each *[RecyclerView](https://developer.android.com/reference/android/support/v7/widget/RecyclerView)*'s item its own *Lifecycle* and gaining all the advantages of being able to interact with any *Lifecycle-Aware Component*.

## Download
```groovy
dependencies {
    //...
    implementation 'dev.sarquella.lifecyclecells:lifecyclecells:1.0.2'
}
```

## Sample Project
A basic [sample](https://github.com/Sarquella/LifecycleCells/tree/master/app) is given to demonstrate how the provided *ViewHolder*'s *Lifecycle* can be used to interact with a *Lifecycle-Aware Component* such as a *LiveData* instance.

## Usage
The library is composed by 3 main abstract classes:

* **LifecycleViewHolder**: A simple abstract *ViewHolder* subclass that just implements the *LifecycleOwner* interface, with the methods to update its *LifeCycle* when necessary.

* **LifecycleAdapter**: A simple abstract *[Adapter](https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter)* subclass that is in charge of calling the corresponding `LifecycleViewHolder` methods for updating its *LifeCycle* when needed.

* **LifecyclePagedListAdapter**: Works the same way as `LifecycleAdapter`, but it subclasses *[PagedListAdapter](https://developer.android.com/reference/android/arch/paging/PagedListAdapter)* in order to be compatible with the *[Paging Library](https://developer.android.com/topic/libraries/architecture/paging/)*.

### Usage Steps
1. Make your *ViewHolder* extend `LifecycleViewHolder`:
  
	```kotlin
	class MyViewHolder(itemView: View) : LifecycleViewHolder(itemView) {
		//...
	} 
	```

2. Make your *Adapter* extend `LifecycleAdapter`:

	```kotlin
	class MyAdapter : LifecycleAdapter<MyViewHolder>() {
		//...
	}
	```

3. **Congratulations!** 🙌 Your *ViewHolder* is now a *LifecycleOwner* 😋

<br>

**NOTE:** If you want to use the **_Paging Library_**, make your *Adapter* extend `LifecyclePagedListAdapter` instead:

```kotlin
class MyPagedListAdapter : LifecyclePagedListAdapter<MyListType, MyViewHolder>(DIFF_CALLBACK) {
	//...
}
```
<br>

### Using a custom Adapter (Less Recommended)
If you are already extending any other custom *Adapter* and can not make it extend `LifecycleAdapter` nor `LifecyclePagedListAdapter`, you can still make it compatible with `LifecycleViewHolder` by overriding the following *Adapter*'s methods:

* **onViewAttachedToWindow**
* **onViewDetachedFromWindow**
* **onViewRecycled**

Override them as follows:

```kotlin
class MyAdapter : AnyCustomAdapter<MyViewHolder>() {

	override fun onViewAttachedToWindow(holder: MyViewHolder) {
		super.onViewAttachedToWindow(holder)
        
		//Notifies LifecycleViewHolder it has been attached
		holder.onAttached() 
	}

	override fun onViewDetachedFromWindow(holder: MyViewHolder) {
		super.onViewDetachedFromWindow(holder)
        
		//Notifies LifecycleViewHolder it has been detached
		holder.onDetached()
	}

	override fun onViewRecycled(holder: MyViewHolder) {
		super.onViewRecycled(holder)
        
		//Notifies LifecycleViewHolder it has been recycled
		holder.onRecycled()
	}
    
	//...
}
```

## Observing LiveData
There is no doubt that one of the main purposes for providing a *Lifecycle* to the *ViewHolder* class is to be able to use it for observing a *LiveData* instance.

In order to do so, we first need to obtain the *LiveData* instance.
There are several ways of obtaining it and may vary on each situation. However, for most common cases, the recommended way is by obtaining it following the *Google*'s proposed **_MVVM_** pattern. This way, the *LiveData* instance will be provided by the *[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)*.

Please, refer to this [*StackOverflow* question](https://stackoverflow.com/questions/47453261/android-architecture-components-using-viewmodel-for-recyclerview-items) to find out how to use a *ViewModel* with a *ViewHolder*.

Finally, just get the *LiveData* instance through the *ViewModel* to observe it from the *ViewHolder* extending from `LifecycleViewHolder`:

```kotlin
class MyViewHolder(itemView: View) : LifecycleViewHolder(itemView) {
	//...
	fun observeLiveData() {
	    viewModel.liveDataInstance.observe(this, Observer {
	      //...
	    })
	}
	//...
} 
```

## License
[LICENSE](https://github.com/Sarquella/LifecycleCells/blob/master/LICENSE)

```
Copyright 2019 Adrià Sarquella Farrés

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```