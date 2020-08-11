
All 6 sections completed and one extension

To run the program:

Use the format given in the instructions:

Javac Administrator.java			 | to compile
java Administrator filename daysToRun		 | to run with the given config and amount of days

Where file name is the name of the config file (including .txt, for example: mySchool.txt)
daysToRun is the amount of days to run the simulation for (integer)



Extension:
Allowed the current state of the school to be output to a file in the correct fromat to be continued by giving that file as the configuration file.

At the end of the simulation the user will be asked if they would like to save (Y/N)
if it is to be saved please enter Y,
you will then be asked to provide a file name, please include including .txt (for example: save.txt)
this file can then be used as the configuration file if you would like to continue the simulation

To save the courses currently active, the saved file needs additional information.

 -- additional information should not be included in the first configuration file --
     -- It is only used to recreate the state of the school from a saved file --

additional information added for this is marked with "savedata".

Format for students:
student:name,gender,age,certificates(savedata),studentid(savedata)

the certificates are a string with each subject ID seperated by "-"

Format for instructors:
instructor:name,gender,age,ID(savedata)

instructor is replaced by the kind of instructor they are, eg Teacher

Format for courses (all only used for save data):
course:subjectID,daysUntilStart,daysToRun,instructorID,studentIDs

the student IDs are a string with each ID speeratede by seperated by "-"

