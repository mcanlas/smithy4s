"use strict";(self.webpackChunksmithy4s=self.webpackChunksmithy4s||[]).push([[573],{3905:function(e,t,n){n.d(t,{Zo:function(){return c},kt:function(){return m}});var r=n(7294);function i(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function o(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function a(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?o(Object(n),!0).forEach((function(t){i(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):o(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,r,i=function(e,t){if(null==e)return{};var n,r,i={},o=Object.keys(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||(i[n]=e[n]);return i}(e,t);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)n=o[r],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(i[n]=e[n])}return i}var l=r.createContext({}),p=function(e){var t=r.useContext(l),n=t;return e&&(n="function"==typeof e?e(t):a(a({},t),e)),n},c=function(e){var t=p(e.components);return r.createElement(l.Provider,{value:t},e.children)},u={inlineCode:"code",wrapper:function(e){var t=e.children;return r.createElement(r.Fragment,{},t)}},d=r.forwardRef((function(e,t){var n=e.components,i=e.mdxType,o=e.originalType,l=e.parentName,c=s(e,["components","mdxType","originalType","parentName"]),d=p(n),m=i,h=d["".concat(l,".").concat(m)]||d[m]||u[m]||o;return n?r.createElement(h,a(a({ref:t},c),{},{components:n})):r.createElement(h,a({ref:t},c))}));function m(e,t){var n=arguments,i=t&&t.mdxType;if("string"==typeof e||i){var o=n.length,a=new Array(o);a[0]=d;var s={};for(var l in t)hasOwnProperty.call(t,l)&&(s[l]=t[l]);s.originalType=e,s.mdxType="string"==typeof e?e:i,a[1]=s;for(var p=2;p<o;p++)a[p]=n[p];return r.createElement.apply(null,a)}return r.createElement.apply(null,n)}d.displayName="MDXCreateElement"},1105:function(e,t,n){n.r(t),n.d(t,{contentTitle:function(){return l},default:function(){return d},frontMatter:function(){return s},metadata:function(){return p},toc:function(){return c}});var r=n(7462),i=n(3366),o=(n(7294),n(3905)),a=["components"],s={sidebar_label:"SimpleRestJson",title:"The SimpleRestJson protocol"},l=void 0,p={unversionedId:"protocols/simple-rest-json/overview",id:"protocols/simple-rest-json/overview",title:"The SimpleRestJson protocol",description:"Smithy4s provides a custom Json-in/Json-out protocol that smithy services can be annotated with.",source:"@site/../docs/target/jvm-2.13/mdoc/03-protocols/02-simple-rest-json/01-overview.md",sourceDirName:"03-protocols/02-simple-rest-json",slug:"/protocols/simple-rest-json/overview",permalink:"/smithy4s/docs/protocols/simple-rest-json/overview",editUrl:"https://github.com/disneystreaming/smithy4s/edit/main/modules/docs/src/03-protocols/02-simple-rest-json/01-overview.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_label:"SimpleRestJson",title:"The SimpleRestJson protocol"},sidebar:"tutorialSidebar",previous:{title:"Protocols and smithy4s",permalink:"/smithy4s/docs/protocols/protocols"},next:{title:"Server",permalink:"/smithy4s/docs/protocols/simple-rest-json/server"}},c=[{value:"Semantics",id:"semantics",children:[],level:2},{value:"Example spec",id:"example-spec",children:[],level:2},{value:"Supported taits",id:"supported-taits",children:[],level:2},{value:"Decoding and encoding unions",id:"decoding-and-encoding-unions",children:[{value:"Tagged union",id:"tagged-union",children:[],level:3},{value:"Untagged union",id:"untagged-union",children:[],level:3},{value:"Discriminated union",id:"discriminated-union",children:[],level:3}],level:2}],u={toc:c};function d(e){var t=e.components,n=(0,i.Z)(e,a);return(0,o.kt)("wrapper",(0,r.Z)({},u,n,{components:t,mdxType:"MDXLayout"}),(0,o.kt)("p",null,"Smithy4s provides a custom Json-in/Json-out protocol that smithy services can be annotated with."),(0,o.kt)("p",null,"Smithy4s comes with opt-in http4s-specific module, that contains functions that are aware of this protocol, and can be used to quickly derive http services and clients."),(0,o.kt)("p",null,"As for the json aspect of the protocol, ",(0,o.kt)("a",{parentName:"p",href:"https://github.com/plokhotnyuk/jsoniter-scala/"},"jsoniter-scala")," is used for the (de)serialisation of the http bodies."),(0,o.kt)("h2",{id:"semantics"},"Semantics"),(0,o.kt)("p",null,"In this protocol, the values in shapes are bound to http metadata or body according to the specification of the ",(0,o.kt)("a",{parentName:"p",href:"https://awslabs.github.io/smithy/1.0/spec/core/http-traits.html?highlight=http#http-binding-traits"},"Http Binding traits"),". However, the ",(0,o.kt)("inlineCode",{parentName:"p"},"@mediaType")," trait has no incidence, and all bodies (when present) are serialised in JSON."),(0,o.kt)("h2",{id:"example-spec"},"Example spec"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-kotlin"},'namespace smithy4s.example\n\nuse smithy4s.api#simpleRestJson\n\n@simpleRestJson\nservice HelloWorldService {\n  version: "1.0.0",\n  // Indicates that all operations in `HelloWorldService`,\n  // here limited to the Hello operation, can return `GenericServerError`.\n  errors: [GenericServerError],\n  operations: [Hello]\n}\n\n@error("server")\n@httpError(500)\nstructure GenericServerError {\n  message: String\n}\n\n@http(method: "POST", uri: "/{name}", code: 200)\noperation Hello {\n  input: Person,\n  output: Greeting\n}\n\nstructure Person {\n  @httpLabel\n  @required\n  name: String,\n\n  @httpQuery("town")\n  town: String\n}\n\nstructure Greeting {\n  @required\n  message: String\n}\n')),(0,o.kt)("h2",{id:"supported-taits"},"Supported taits"),(0,o.kt)("p",null,"This protocol and its interpreters, are aware of the following traits provided out of the box:"),(0,o.kt)("ul",null,(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("a",{parentName:"li",href:"https://awslabs.github.io/smithy/1.0/spec/core/model.html#simple-shapes"},"all simple shapes")),(0,o.kt)("li",{parentName:"ul"},"composite data shapes, including collections, unions, structures."),(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("a",{parentName:"li",href:"https://awslabs.github.io/smithy/1.0/spec/core/model.html#service"},"operations and services")),(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("a",{parentName:"li",href:"https://awslabs.github.io/smithy/1.0/spec/core/constraint-traits.html#enum-trait"},"enumerations")),(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("a",{parentName:"li",href:"https://awslabs.github.io/smithy/1.0/spec/core/type-refinement-traits.html#error-trait"},"error trait")),(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("a",{parentName:"li",href:"https://awslabs.github.io/smithy/1.0/spec/core/http-traits.html"},"http traits"),", including ",(0,o.kt)("strong",{parentName:"li"},"http"),", ",(0,o.kt)("strong",{parentName:"li"},"httpError"),", ",(0,o.kt)("strong",{parentName:"li"},"httpLabel"),", ",(0,o.kt)("strong",{parentName:"li"},"httpHeader"),", ",(0,o.kt)("strong",{parentName:"li"},"httpPayload"),", ",(0,o.kt)("strong",{parentName:"li"},"httpQuery"),", ",(0,o.kt)("strong",{parentName:"li"},"httpPrefixHeaders"),", ",(0,o.kt)("strong",{parentName:"li"},"httpQueryParams"),"."),(0,o.kt)("li",{parentName:"ul"},(0,o.kt)("a",{parentName:"li",href:"https://awslabs.github.io/smithy/1.0/spec/core/protocol-traits.html?highlight=timestampformat#timestampformat-trait"},"timestampFormat trait"))),(0,o.kt)("h2",{id:"decoding-and-encoding-unions"},"Decoding and encoding unions"),(0,o.kt)("p",null,"There is no one way to encode a union in JSON. You can use tagged union, untagged union or discriminated union. Smithy4s supports all three of them. The following example assume the following shapes are available:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre"},"structure One {\n  one: String\n}\n\nstructure Two {\n  two: Int\n}\n")),(0,o.kt)("h3",{id:"tagged-union"},"Tagged union"),(0,o.kt)("p",null,"This is the default behaviour, and it matches Smithy's encoding of union. The rational is simple: there is a key indicating what shape we should decode/encode to. Given the following union:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre"},"union Tagged {\n  first: One,\n  second: Two\n}\n")),(0,o.kt)("p",null,"Smithy4s will render encode / decode an array of ",(0,o.kt)("inlineCode",{parentName:"p"},"Tagged")," this way:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-json"},'[\n  { "first": { "one": "smithy4s" } },\n  { "second": { "two": 42 } },\n]\n')),(0,o.kt)("h3",{id:"untagged-union"},"Untagged union"),(0,o.kt)("p",null,"Untagged union are supported via an annotation: ",(0,o.kt)("inlineCode",{parentName:"p"},"@untagged"),". Here, there is no way for the encoder/decoder to know what it is working with. It will do a best effort to figure out what shape it is working with given the content. Given the following union:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre"},"use smithy4s.api#untagged\n\n@untagged\nunion Untagged {\n  first: One,\n  second: Two\n}\n")),(0,o.kt)("p",null,"Smithy4s will render encode / decode an array of ",(0,o.kt)("inlineCode",{parentName:"p"},"Untagged")," this way:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-json"},'[\n  { "one": "smithy4s" },\n  { "two": 42 },\n]\n')),(0,o.kt)("h3",{id:"discriminated-union"},"Discriminated union"),(0,o.kt)("p",null,"Discriminated union are supported via an annotation: ",(0,o.kt)("inlineCode",{parentName:"p"},'@discriminated("tpe")'),". Here, the encoder/decoder looks for the field specified in the annotation to know what shape it is working with. Given the following union:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre"},'use smithy4s.api#discriminated\n\n@discriminated("tpe")\nunion Untagged {\n  first: One,\n  second: Two\n}\n')),(0,o.kt)("p",null,"Smithy4s will render encode / decode an array of ",(0,o.kt)("inlineCode",{parentName:"p"},"Untagged")," this way:"),(0,o.kt)("pre",null,(0,o.kt)("code",{parentName:"pre",className:"language-json"},'[\n  { "tpe": "first",  "one": "smithy4s" },\n  { "tpe": "seconf", "two": 42 },\n]\n')))}d.isMDXComponent=!0}}]);