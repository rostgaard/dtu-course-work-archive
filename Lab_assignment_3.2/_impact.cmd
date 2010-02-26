setMode -bs
setMode -bs
setCable -port auto
Identify 
identifyMPM 
setMode -bs
addDevice -p 3 -file "C:/Documents and Settings/s072923/Desktop/Lab_assignment_3.2/latch8_test.bit"
deleteDevice -position 4
Program -p 3 
saveProjectFile -file "C:/Documents and Settings/s072923/Desktop/Lab_assignment_3.2/Lab_assignment_3.ipf"
setMode -bs
deleteDevice -position 1
deleteDevice -position 1
deleteDevice -position 1
setMode -ss
setMode -sm
setMode -hw140
setMode -spi
setMode -acecf
setMode -acempm
setMode -pff
setMode -bs
