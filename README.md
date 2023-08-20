# stopwatch-app
 
The issue with this project i that when I try to launch the coroutine scope i get the following exception: "Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'".
In the build.gradle file I've tried all possible combinations with the implementations (removing or adding the core/android/test versions), but I keep getting similar errors.
