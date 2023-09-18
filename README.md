# Macchiato-Lungo-Language
This is a Project written in Java, consists of the solution code and unit tests for every required feature.  
**Course**: Object-Oriented Programming (2nd semester)  
**Degree**: Bachelor's at University of Warsaw    


## Abstract
This projects allows you to create, run and debug your code in an artificial **Macchiato Lungo** language.  
Every instruction is actually **simulated in Java code**, which serves as a **virtual environment for running and debugging** your Macchiato Lungo programs.

## Features
In **Macchiato Lungo** you can:
- run the program inside the Macchiato Lungo Interpreter,
- run & debug the program with the Macchiato Lungo Debugger,
- use integer variables,
- make if/else-if instructions,
- create for loops
- declare and call parametrized procedures,
- shadow variables in nested scopes,
- dump memory state to a file.

Creating Macchiato Lungo programs uses a *ProgramBuilder* Design Pattern.
Debugging has following features:
- **continue**
- **step ```cnt```** instructions
- **display ```num```** all variables in a ```num``` levels higher scope  
- **dump** all current scope variables' names and values to a file
- **exit**

## Demo 
<img width="526" alt="image" style="float: left; margin-right: 10" src="https://github.com/Andreluss/Macchiato-Lungo-Language/assets/64368904/8a3bc251-79a8-4f6a-a72a-5963b945c435">
<img width="391" alt="image" style="float: left; margin-right: 10" src="https://github.com/Andreluss/Macchiato-Lungo-Language/assets/64368904/7a0dc032-29a9-4fac-befd-d045031c4bb3">  

Reference the [```Main.Java```](https://github.com/Andreluss/Macchiato-Lungo-Language/blob/master/src/runtime/Main.java) file for more.
