# Network Class - SMTP Spam Client Exercise

17 april 2018 - HEIG-VD 2nd year - by Adrien Allemand and Loyse Krug

## Intro

This is our school graded project featuring a SMTP Client designed to **send pranks to a list of email adresses**. 

## Objectives

In this lab, we were asked to develop a simple client application (TCP) in Java. This client application uses Socket API to prank a given SMTP server with forged e-mails. The main objectives were :

-   Become intimate with the **SMTP protocol** and learn the details and poetry of communicating with a **SMTP server**.
-   Approach the notion of **mock server**, in the hope of not being blacklisted by google itself !
-   Be witness to the supreme power and surprising ease of use of the  **forged email client** we just gave birth to.
-   Apply the elegance of an **object-oriented model** to the solution we offer.

## Functional requirements

As stated by our professor at https://github.com/SoftEng-HEIGVD/Teaching-HEIGVD-RES-2018-Labo-SMTP

Your mission is to develop a client application that automatically plays pranks on a list of victims:

-   The user should be able to **define a list of victims** (concretely, you should be able to create a file containing a list of e-mail addresses).
-   The user should be able to **define how many groups of victims should be formed** in a given campaign. In every group of victims, there should be 1 sender and at least 2 recipients (i.e. the minimum size for a group is 3).
-   The user should be able to **define a list of e-mail messages**. When a prank is played on a group of victims, then one of these messages should be selected. **The mail should be sent to all group recipients, from the address of the group sender**. In other words, the recipient victims should be lead to believe that the sender victim has sent them.

## User Manual

The user manual is separated onto **two parts** : first we will explain how to easely **setup our mook server** with the help of docker, secondly we will go through the whole **configuration of the client** so you can benefit of **quality pranks**.

### Setup the mook server

The setup of the mook server will be fairly easy as we use a docker container that is available on dockerhub. 

If you don't have a working install of docker please go to there website and install it : 
https://www.docker.com/community-edition



