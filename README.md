# ANTLRv4 support in IntelliJ IDEs

[![](https://git.karmakrafts.dev/kk/antlr4-intellij-adaptor/badges/master/pipeline.svg)](https://git.karmakrafts.dev/kk/antlr4-intellij-adaptor/-/pipelines)
[![](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo.maven.apache.org%2Fmaven2%2Fdev%2Fkarmakrafts%2Fantlr4%2Fantlr4-intellij-adaptor%2Fmaven-metadata.xml
)](https://git.karmakrafts.dev/kk/antlr4-intellij-adaptor/-/packages)
[![](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fcentral.sonatype.com%2Frepository%2Fmaven-snapshots%2Fdev%2Fkarmakrafts%2Fantlr4%2Fantlr4-intellij-adaptor%2Fmaven-metadata.xml
)](https://git.karmakrafts.dev/kk/antlr4-intellij-adaptor/-/packages)

**This is a fork of the original library to update tooling and provide more regular fixes and builds if needed.**  
**Check out the original project [here](https://github.com/antlr/antlr4-intellij-adaptor)**

A library to support the use of ANTLRv4 grammars for custom languages in IntelliJ-based IDEs plug-in development.

This library has adaptors that convert ANTLR-generated parse trees into IntelliJ PSI trees.  Mostly this library is about adapting ANTLR parsers and trees, but there is considerable support to examine PSI trees derived from ANTLR parse trees. For example, if you're building a structure view for your plug-in and you want to get the list of function names you can use XPath-like specs such as `"/script/function/ID"`:

```java
Collection<? extends PsiElement> allfuncs =
    XPath.findAll(SampleLanguage.INSTANCE, tree,
                  "/script/function/ID");
```

## Using the library in your project

The library is [published on Maven Central](https://search.maven.org/search?q=a:antlr4-intellij-adaptor) which means you can download the JAR and add it to your classpath manually, or pull the dependency automatically if you are using a Gradle build:

```groovy
repositories {
    mavenCentral()
}

dependencies {
    compile "dev.karmakrafts.antlr4:antlr4-intellij-adaptor:VERSION"
}
```

In Maven builds, use:

```xml
<dependency>
  <groupId>dev.karmakrafts.antlr4</groupId>
  <artifactId>antlr4-intellij-adaptor</artifactId>
  <version>VERSION</version>
</dependency>
```

You can now head over to the [Getting started section](https://github.com/antlr/antlr4-intellij-adaptor/wiki/Getting-started) of the wiki.

## Examples

Here is a list of known plugins that use the adaptor:

* [Sample IntelliJ plugin](https://github.com/antlr/jetbrains-plugin-sample)
* [ANTLRv4 grammar plugin](https://github.com/antlr/intellij-plugin-v4)
* [Pebble plugin](https://github.com/bjansen/pebble-intellij)

Other usages can be [found on GitHub](https://github.com/search?p=1&q=ANTLRParserAdaptor&type=Code)