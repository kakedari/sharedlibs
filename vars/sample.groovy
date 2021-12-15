int courseCount = 4;
Boolean isProgrammer = true;
String[] singers = ["Bob","Tom","Jeff","Roy"]

if (isProgrammer){
  println "He is a Programmer"
}
else {
  println "He is not a programmer"
}

for (int i=0; i<courseCount; i++){
  println "Chirs made course" + (i+1) + "!!!"
}

for (String singer : singers){
  println singer
}
