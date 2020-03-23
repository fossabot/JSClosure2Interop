<img src="https://github.com/didier-durand/JSClosure2Interop/blob/master/img/js-logo.jpg" height="100">   <img src="https://github.com/didier-durand/JSClosure2Interop/blob/master/img/gwt-logo.png" height="100"><img src="https://github.com/didier-durand/JSClosure2Interop/blob/master/img/java-logo.png" height="100">

# JSClosure2Interop

![Java CI with Maven](https://github.com/didier-durand/JSClosure2Interop/workflows/Java%20CI%20with%20Maven/badge.svg)

Javascript Closures to Java Source Code (JSInterop Format)

Twitter: [@didierdurand](https://twitter.com/chaingrok)

This is WIP : Work In Progress. Patience required.

This project uses two other projects to generate Java bindings respecting the JSInterop specs for standard Javasscript libraries:

-   [JSInterop Generator](https://github.com/google/jsinterop-generator) by Google: "The jsinterop generator is a java program that takes closure extern files as input and generates Java classes annotated with JsInterop annotations."
-   [Closure Compiler](https://github.com/google/closure-compiler) by Google: the Closure compiler publishes optimized interfaces of widely-used Javascript libraries. 

1) https://github.com/google/jsinterop-generator : The jsinterop generator is a java program that takes closure extern files as input and generates Java classes annotated with JsInterop annotations.
2) https://github.com/google/closure-compiler : Javascript interfaces of widely-used Javascript libraries produced by the Javascript Closure Compiler (produced by Google)

Description of JSInterop annotations : [JsInterop v1.0: Nextgen GWT/JavaScript Interoperability](https://docs.google.com/document/d/10fmlEYIHcyead_4R1S5wKGs1t2I7Fnp_PaNaa7XTEk0/edit#heading=h.o7amqk9edhb9)

The purpose of ths project is to obtain Java bindings for those Java libraries and interfaces. They will be used in products like GWT integrating Java and Javascript for application front-ends.
Parameters for ClosureJsinteropGenerator_deploy.jar

## options for JSInterop generator

-   --debug_mode : debug mode
-   --strict : converts warnings to errors during reverse transpiling
-   --output : jar path for generated java source code
-   --extension_type_prefix: TBD
-   --package_prefix : package name for generated classes
-   --global_scope_class_name : head class name
-   --output_dependency_file : dependency file
