#Passport

##Goals
Based on a the original idea of HackerSpaces passports, by [Name and Link Here]

This aims to create an electronic passport that can be backed up by a physical one,
either for "legacy" HackerSpaces and for vanity reasons.
We expand the concept to incorporate members, and also for lonely hackers out
there who travel between HackerSpaces. 

##System description
###Hacker
Hackers are our users, they register in our site, and can have their Passports 
checked in at any HackerSpace that uses the system (not necessarily our service). 
Upon creation we only require some basic information, and you will be ready to 
go and get your Passport stamped at every HackerSpace you visit, as well the local
HackerSpace (or spaces) that you are member of.

####The Passport
Each hacker has his passport in the service that he likes most, he can even print 
his and carry it around between HackerSpaces (even to those who live in the 70's)

###HackerSpace
A HackerSpace is an association/organization, that serves the hacker community
[Insert Link to Description].  
Our system allows for HackerSpaces to check members and visitors passports in a
both easy and secure way. 

###Events
Events aren't a required part of the passport but decided to go with this 
concept to allow HackerSpaces interested in share their events with nearby
hackers that might not be aware of them, or travelers to plan their trips better.  

###Connections

This system is all about connecting hackers and HackerSpaces.
There are three kinds of interactions possible within this system, see the descriptions
as a general guideline to what connection you want to stablish (the names are self explanatory):

**Hacker to Space** In order to connect hackers and HackerSpaces we describe the interactions between them like this: 

1.	**Visit:**  
	A hacker comes in for a visit of your HackerSpace.

2.	**Guest Visit:**  
	Some hacker is invited by your HackerSpace to share his knowledge, you might
	distinguish him this way.

3.	**Member:**  
	Your members will like to be acknowledged as so this way.

4.	**Founder:**  
	Yep this guy founded your HackerSpace, doesn't he deserves the acknowledgment?

6.	**Other:**  
	That participation can´t fit in any of the above, but you know that his passport
	must reflect his relationship to your HackerSpace?

**Space to Space** Two HackerSpaces can also stablish a connection in one of the following ways:

1.	**Inspiration:**  
	Your HackerSpace is based on that one, your structure and values are based
	upon theirs (one-way)

2.	**Sister:**  
	Sister HackerSpaces, have a special connection, your goals are very similar 
	but you are physically far away, or although you can have different core values,
	you want to get more connected. Based on the sister cities concept.
	
**Hacker to Hacker**
There is also another special category of relationships between two hackers
(if there is a third hacker, you better start your own HackerSpace xD )

1.	**Inspiration**  
	You can set another hacker as your personal inspiration (one-way)

**Hacker to Event**


1.	**Organizer**
	The humans who give sweat and blood for the event
2.	**Guest**
	You wanted this guy on your event, he can be the event in himself
3.	**Visitor**
	People who come to see your event


**Event to HackerSpace**

1.	**Organizer**
	The organizing HackerSpaces
2.	**Guest**
	When you extend a special invite to one HackerSpace because you feel that their presence
	will greatly improve your event, make sure you also point their speakers as guests
3.	**Visitor**
	If a HackerSpace sends a sizable delegation (1 or more, your criteria) to your event,
	you can check the HackerSpace as a visitor

##Technical Information

###API
To allow comunication with other instances of Passport and to allow other clients, 
we create an API to register your visitors, see the API documentation here [Insert Link]

###Security
We don't want anyone to go around and say that he was at a give space when
he wasn't, so our system verifies these claims by having the visited to digitaly
sign that information. This verification is made inside the system, you just have to 
say that he was really there.
It's pretty much straight forward as ABC.
