function toBase64Func () {
    /*转换base64*/
    var img = document.getElementById('imgfile')
    var imgFile = new FileReader();
    imgFile.readAsDataURL(img.files[0]);

    imgFile.onload = function () {
        var imgData = this.result; //base64数据
        console.log(imgData);
    }
}