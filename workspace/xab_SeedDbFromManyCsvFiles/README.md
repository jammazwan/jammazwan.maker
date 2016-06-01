### Camel Keeper: Jammazwan

"Jammazwan" means "camel keeper" [in Hindi](https://books.google.com/books?id=_kWROaer5UsC&pg=PA1138&lpg=PA1138&dq=jammazwan+camel+keeper+hindi&source=bl&ots=7FaF5BXK_F&sig=Cg-U5ORP3dHrFycaCFvo34GdpZ0&hl=en&sa=X&ved=0ahUKEwj8v4OV3YbNAhVjpIMKHSYUB_oQ6AEIHDAA#v=onepage&q=jammazwan%20camel%20keeper%20hindi&f=false). Camel, in this instance, refers to [**Apache Camel**](http://camel.apache.org/), which is sponsored and maintained by Red Hat, and a part of it's [Fuse](https://www.redhat.com/en/technologies/jboss-middleware/fuse) offering.

---

### SeedDbFromManyCsvFiles

Welcome to SeedDbFromManyCsvFiles

The prefix xab is used for maintaining sort order in a directory, providing packaging, and is otherwise meaningless

This project is a code demo project - designed to isolate functionality and make it easy replicate-able. Specifically:

**_Uses JPA and biny to seed a database from many different csv files_**

### Dependencies, First:

__Relax!__ This project runs __almost__ all by itself! No huge web of parent poms to obfuscate dependencies and make learning difficult.

But it does have *one* common dependency which it shares with other jammazwan code examples. 
This design tries to move some of the clutter out of the project, 
so you can focus only on what is required to demo the **SeedDbFromManyCsvFiles** feature.

So you always have to git clone jammazwan.shared into the same folder first, 
then run mvn install on that first, before messing with this project.