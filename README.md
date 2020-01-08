
# react-native-card-connect-react-library

## Getting started

`$ npm install react-native-card-connect-react-library --save`

### Mostly automatic installation

`$ react-native link react-native-card-connect-react-library`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-card-connect-react-library` and add `RNCardConnectReactLibrary.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNCardConnectReactLibrary.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNCardConnectReactLibraryPackage;` to the imports at the top of the file
  - Add `new RNCardConnectReactLibraryPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-card-connect-react-library'
  	project(':react-native-card-connect-react-library').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-card-connect-react-library/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-card-connect-react-library')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNCardConnectReactLibrary.sln` in `node_modules/react-native-card-connect-react-library/windows/RNCardConnectReactLibrary.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Card.Connect.React.Library.RNCardConnectReactLibrary;` to the usings at the top of the file
  - Add `new RNCardConnectReactLibraryPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNCardConnectReactLibrary from 'react-native-card-connect-react-library';

// TODO: What to do with the module?
RNCardConnectReactLibrary;
```
  