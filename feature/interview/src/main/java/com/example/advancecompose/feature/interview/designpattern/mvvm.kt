package com.example.advancecompose.feature.interview.designpattern

/**
 * When Activity gets destroyed due to configuration changes, the ViewModel survives. But how exactly? Where does all the magic happens?
 * 1️⃣ We use a ViewModelProvider to instantiate a ViewModel. Its constructor takes a ViewModelStoreOwner.
 * val viewModel = ViewModelProvider(viewModelStoreOwner).get(MyViewModel::class.java)
 *
 * 🎯 What is ViewModelStoreOwner?
 * A ViewModelStoreOwner is an interface.
 * Any class that implements the getViewModelStore() defined by this interface becomes the owner of ViewModelStore.
 *
 * 🎯 What is ViewModelStore?
 * A ViewModelStore can be considered as a container that stores the ViewModels in a HashMap,
 * where the key is string and value is the ViewModel being saved.
 * ViewModelProvider uses a concatenation of the string_key + ViewModel class canonical name.
 * When the ViewModelProvider creates a new ViewModel, it adds the ViewModel to the map in ViewModelStore.
 *
 * 2️⃣ The responsibility of a ViewModelStoreOwner is to:
 * → Retain a ViewModelStore during configuration changes.
 * → Call ViewModelStore.clear() when it is going to be destroyed.
 * → It means that a ViewModelStoreOwner has a Lifecycle. When it’s in the Destroyed state, it notifies the ViewModelStore of its destruction, which notifies the ViewModel.
 *
 * 3️⃣ Here is a list of components that implements ViewModelStoreOwner (so, they becomes ViewModelStoreOwner)
 * → ComponentActivity (extended by AppCompatActivity, which is extended by usual MainActivity)
 * → Fragment
 * → NavBackStackEntry
 *
 * 4️⃣ Now, ComponentActivity implements ViewModelStoreOwner, so it overrides getViewModelStore().
 * → As you can see in the image, mViewModelStore can’t be null.
 * → To avoid that, the method ensureViewModelStore() is called. If there was a previous ViewModelStore, it’s retrieved, otherwise, a new ViewModelStore is instantiated.
 *
 * 5️⃣ Inside the ensureViewModelStore() method, you can see a call to getLastNonConfigurationInstance().
 * → It’s a method of the Activity class that returns a NonConfigurationInstances. The class is declared in the ComponentActivity.
 * → NonConfigurationInstances is a static class. Static classes object are not bound to any activity or fragment.
 * → As you can see in the definition of NonConfigurationInstances, the second parameter is of type ViewModelStore and is used to keep the reference of our ViewModelStore during a configuration change.
 *
 * 6️⃣ Now, getLastNonConfigurationInstance() method returns the result of the previous call to onRetainNonConfigurationInstance().
 * → As you can see in onRetainNonConfigurationInstance() method, we see that if there is an existing ViewModelStore, the method returns a new instance of NonConfigurationInstances containing the retrieved instance of the ViewModelStore, which contains the ViewModel.
 */