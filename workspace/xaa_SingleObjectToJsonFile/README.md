"Jammazwan" [is Hindi](href="https://books.google.com/books?id=_kWROaer5UsC&amp;pg=PA1138&amp;lpg=PA1138&amp;dq=jammazwan+camel+keeper+hindi&amp;source=bl&amp;ots=7FaF5BXK_F&amp;sig=Cg-U5ORP3dHrFycaCFvo34GdpZ0&amp;hl=en&amp;sa=X&amp;ved=0ahUKEwj8v4OV3YbNAhVjpIMKHSYUB_oQ6AEIHDAA#v=onepage&amp;q=jammazwan%20camel%20keeper%20hindi&amp;f=false) for "camel keeper", and is [explained in this blog](https://betterologist.net/2016/05/jammazwan-projects-for-learning-apache-camel/).

|[**_a jammazwan?_**](https://betterologist.net/2016/06/jammazwan-for-hire/)|also a jammazwan|
| --- | --- |
|<img class="style-svg" src="https://betterologist.net/wp-content/uploads/2016/05/pete-300x297.jpg" alt="pete" width="116" height="115" />|<img class="style-svg" src="https://betterologist.net/wp-content/uploads/2016/05/jammazwanPhotoSmall.png" alt="jammazwanPhotoSmall" width="200" height="116" />|

---

### SingleObjectToJsonFile 
##### _a Camel example project_

Functionality: **_Create an object and save it as json in a file_**

Instructions: _From your workspace directory, git clone both this and it's sibling, then mvn install it's sibling._

_from your bash shell:_

```bash
cd yourworkspacedirectory
git clone https://github.com/jammazwan/jammazwan.shared.git
git clone https://github.com/jammazwan/xaa_SingleObjectToJsonFile.git
cd jammazwan.shared
mvn install 
```
Now you may import this (or both) into your IDE, either via it's pom, or as a native eclipse project.

This project is a code demo designed to respect your time as a developer, and remove any confusion when you replicate it. See [**_this blog_**](https://betterologist.net/2016/05/jammazwan-projects-for-learning-apache-camel/) for more insights on why this was created.

### Other notes:

This page is generated from [metadata](https://github.com/jammazwan/jammazwan.maker/blob/master/src/main/resources/xyzprojects.json). **Handwritten notes specific to this project, _if any_**, may be found [at NOTES.md](NOTES.md)

Other example Camel projects like this one are indexed at [**_jammazwan.x_index_**](https://github.com/jammazwan/jammazwan.x_index)

### Why the _jammazwan.shared_ dependency?

This design tries to include only what is unique to this example, 
so you can focus only on what is required to demo the **SingleObjectToJsonFile** feature **_in isolation_**.
The single shared project includes dumb artifacts that shouldn't be unique, like:

 * Beans used as value objects for all example projects
 * pom.xml dependencies of a more general nature
 * .csv and other source files for use as data sources
 * generic utility functions not specific to a single project

So you always have to git clone jammazwan.shared as a sibling first, 
then run mvn install, before installing this project.

If you want to replicate the functionality of this project into your own stand-alone project, you will need to pull some dependencies and code from each.

For the record, I should have done the sibling project as a parent, but I have been repulsed by so many deep and nasty nested pom hierarchies that I refused to add even one more parent pom to the public domain. Silly, I know.

### Special Instructions:

If there are any special instructions for this project from the metadata:

_be careful not to ever do the wrong thing, it could be incorrect. Do the right thing instead_

### other notes

The prefix **xaa** is used for maintaining sort order in a directory, providing packaging, and is otherwise meaningless. The x stands for _example_.

This and all other [jammazwan projects](https://github.com/jammazwan) were written in Apache Camel, mostly with [camel-velocity](http://camel.apache.org/velocity.html).