//실행파일, 이미지 파일에 대한 정규표현식 작석(시작은 \ 끝은 $표시)
const reExp = new RegExp("\.(exe|sh|bat|js|msi|dll)$")//실행 파일 막기(.은 확장자 구분하는 것이기 때문에)
const regExpImp = new RegExp("\.(jpg|jpeg|png|gif)$");//이미지 파일만
const maxSize = 1024*1024*20 //파일 최대 사이즈(20MB)

//조건 통과하면 1리턴, 아니면 0 리턴
function fileValidation(fileName,fileSize){
    if(!regExpImp.test(fileName)){
        return 0;
    }else if(RegExp.test(fileName)){
        return 0;
    }else if(maxSize<fileSize){
        return 0;
    }else{
        return 1;
    }
}

document.addEventListener('change',(e)=>{
    if(e.target.id=='files'){

        //'files' 라는 id를 가진 input file element에 지정된 file의 정보를 가져옴
        const fileObj = document.getElementById('files').files;
        console.log("fileObj>>>>>"+fileObj);

        //파일을 다시 추가할 때는 버튼 상태를 원래대로(활성화 상태) 변경
        document.getElementById('regBtn').disabled = false;

        //첨부파일에 대한 정보를 fileZone에 뜨게 하기
        let div=document.getElementById('fileZone');
        div.innerHTML=``;
            //ul=>li로 첨부파일 추가
            let isOk=1;//여러 파일이 모두 검증에 통과해야 하기 때문에 *로 각 파일마다 통과여부 확인
            let ul = `<ul class="list-group list-group-flush">`;
            for(let file of fileObj){
                let validReulst = fileValidation(file.name,file,size);
                isOk*=validReulst;//vaildResult => 현재 파일 통과 여부 / isOk => 지금까지 파일 통과여부
                ul+=`<li class="list-group-item">`;
                ul+=`<div class="mb-3">`;
                ul+=`${validReulst?'<div class="mb-3">업로드 가능</div>':'<div class="mb-3">업로드 불가능</div>'}`;
                ul+=`${file.name}</div>`;
                ul+=`<span class="badge text-bg-${vaildResult ? 'success' : 'danger'}">${file.size}Byte</span></li>`;
            }
            ul+=`</ul>`;
            div.innerHTML=ul;
            if(isOk==0){//1개라도 통과하지 못한 파일이 있다면(isOk=>전체 파일 통과 여부)
                document.getElementById("regBtn").disabled=true;//regBtn비활성화
            }
    }

})

//파일 삭제 함수
async function removeFile(uuid){
    try {
        console.log("진입~~~~333");
        const url ="/board/fileRemove/"+uuid;
        const config={
            method : "delete"
        }
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error)
    }
    }
    
    //파일 삭제 함수 호출
    document.addEventListener('click',(e)=>{
        if(e.target.classList.contains('fileDelBtn')){
            removeFile(e.target.dataset.uuid).then(result=>{
                if(result>0){
                    alert("파일 삭제 성공");
                }else{
                    alert("파일 삭제 실패");
                }
                location.reload();
            })
        }
    
    })