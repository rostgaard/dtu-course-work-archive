
set oWShell = CreateObject("WScript.Shell")
set objFSO = CreateObject("Scripting.FileSystemObject")

objStartFolder = "TrylleTegn"
objEndFolder = "img"

Set objFolder = objFSO.GetFolder(objStartFolder)

Set colFiles = objFolder.Files
counter = 0
For Each objFile in colFiles
	If InStrRev(objFile.name, ".svg") <> 0 Then
		splitArr = Split(objFile.name, "__", -1, 1)
		last = UBound(splitArr)
		
		Folder = objEndFolder & "/"
		If last > 0 Then
			Folder = Folder & splitArr(0) & "/"
			If not objFSO.FolderExists(Folder) Then
				objFSO.CreateFolder(Folder)
			End If
		End If
		
		oWShell.Run("inkscape -f " & objStartFolder & "/" & objFile.Name & " -A " & Folder & Replace(splitArr(last), ".svg", ".pdf")) 
		counter = counter + 1
	End If
Next


WScript.Echo("Done! Converted " & counter & " files to pdf.")