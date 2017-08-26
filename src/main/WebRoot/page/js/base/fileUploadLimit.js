/**
 * 
 */
var FileUpLoadLimit = function(target,limitType, filemaxsize) {
	var fileSize = 0;
	filemaxsize = isNaN(filemaxsize) ? filemaxsize : 10;
	var commonFileTypes = [ ".jpg",".jpeg", ".png",".gif",".tiff",".tif",".rar", ".txt", ".zip", ".doc",
			".ppt", ".xls", ".pdf", ".docx", ".xlsx" ];
	var imgFileTypes = [ ".jpg", ".jpeg", ".png", ".tiff", ".gif", ".tif" ];
	var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
	var filePath = target.value;
	var resultMsg = "true";
	if (filePath) {
		var fileType = [];
		if(limitType == "img"){
			fileType = imgFileTypes;
		} else {
			fileType = commonFileTypes
		}
		var isnext = false;
		var fileend = filePath.substring(filePath.lastIndexOf("."));
		if (fileType && fileType.length > 0) {
			for (var i = 0; i < fileType.length; i++) {
				if (fileType[i] == fileend) {
					isnext = true;
					break;
				}
			}
		}
		if (!isnext) {
			resultMsg = "不接受此文件类型！";
			target.value = "";
			return resultMsg;
		}
	} else {
		resultMsg = "附件不存在，请重新输入！";
		return resultMsg;
	}

	if (isIE && !target.files) {
		var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
		if (!fileSystem.FileExists(filePath)) {
			resultMsg = "附件不存在，请重新输入！";
			return resultMsg;
		}
		var file = fileSystem.GetFile(filePath);
		fileSize = file.Size;
	} else {
		fileSize = target.files[0].size;
	}

	var size = fileSize/1024/1024;
	if (size > filemaxsize) {
		resultMsg = "附件大小不能大于" + filemaxsize + "M！";
		target.value = "";
		return resultMsg;
	}
	if (size <= 0) {
		resultMsg = "附件大小不能为0M！";
		target.value = "";
		return resultMsg;
	}
	return resultMsg;
}