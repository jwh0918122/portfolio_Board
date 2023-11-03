console.log("bnoVal2>>"+bnoVal);

//댓글 등록 함수
async function postCommentToServer(cmtData){
    try {
        const url ="/comment/commentWrite";
        const config ={
            method : "post",
            headers :{
                'content-type': 'application/json; charset=utf-8'
            },
            body : JSON.stringify(cmtData)
        };
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

//댓글 등록함수 호출
document.getElementById("cmtPostBtn").addEventListener('click',()=>{
    //bno, writer, content
   console.log("vvvvvvvvvvvvv");
    let cmtWriter=document.getElementById("w").value; //일단 작성자(나중에 로그인 아이디로 수정)
    let cmtText=document.getElementById("cmtText").value;

    console.log("cmtWriter>>"+cmtWriter);
    console.log("cmtText>>"+cmtText);
    if(cmtText ==null || cmtText ==""){
        alert("댓글을 입력해 주세요")
        document.getElementById("cmtText").focus();
    }else{
        let cmtData={
            bno : bnoVal,
            writer : cmtWriter,
            content : cmtText
        }
        postCommentToServer(cmtData).then(result=>{
            if(result>0){
                alert("댓글 등록 성공");
            }else{
                alert("댓글 등록 실패");
            }
            commentListPrint(bnoVal);
        })
    }
})

//댓글 리스트 요청 함수
async function commentList(bnoVal,page){
    try {
        const url="/comment/list/"+bnoVal+"/"+page;
        const resp = await fetch(url);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error)
    }
}

//댓글 요청 함수 호출
function commentListPrint(bnoVal,page=1){
    commentList(bnoVal,page).then(result=>{ //result는 ph => pgvo(pageNo,qty), cmtList
    let div=document.getElementById('accordionExample');
    div.innerHTML=``;
        if(result.cmtList.length>0){  
            let str=``;
            for(let cvo of result.cmtList){
                str+=`<div class="accordion-item" data-cno="${cvo.cno}" data-writer="${cvo.writer}>`;
                str+=`<div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">`;
                str+=`<div class="accordion-body">사람 이모티콘,${cvo.writer}${cvo.regAt}</div>`;
                str+=`<div class="box2">`;
                str+=`<button type="button" class="btn btn-outline-primary detailBtn2">수정</button>`;
                str+=`<button type="button" class="btn btn-outline-primary detailBtn2">삭제</button>`;
                str+=`</div></div>`;
                str+=`<div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">`;
                str+=` <input type="text">`;
                str+=`</div></div></div>`;
            }
            div.innerHTML+=str;
        }
    })
} 


/* <div class="accordion" id="accordionExample">

<div class="accordion-item">
    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
        <div class="accordion-body">사람 이모티콘, 닉네임(writer...?), regDate</div>
        <div class="box2">
            <button type="button" class="btn btn-outline-primary detailBtn2">수정</button>
            <button type="button" class="btn btn-outline-primary detailBtn2">삭제</button>
        </div>
    </div>
    
        <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
            <input type="text">
        </div>			
</div>		
</div>  */