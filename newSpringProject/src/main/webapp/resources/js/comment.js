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
            document.getElementById("cmtText").value='';
            document.getElementById("cmtText").focus();
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
    let ul=document.getElementById('cmtListContainer');
    console.log("cmtList>>>>"+result.cmtList);
        if(result.cmtList.length>0){  
            //다시 댓글을 뿌릴 때 기존 값 삭제 1page 경우
            if(page==1){
                ul.innerText="";
            }   
            let str=``;
            for(let cvo of result.cmtList){
                str+=`<li class="list-group-item" aria-disabled="true" data-cno="${cvo.cno}" data-writer="${cvo.writer}">`;
                str+=`<div class="div1"><span>사람 아이콘, ${cvo.writer} reg: ${cvo.regAt} ${cvo.regAt != cvo.modAt? "mod: '${cvo.modAt}'":""}</span>`;
                str+=`<div><button type="button" id="cmtModBtn" class="btn btn-primary mb-3 cmtBtn modBtn">수정</button>`;
                str+=`<button type="button" id="cmtDelBtn" class="btn btn-primary mb-3 cmtBtn delBtn">삭제</button></div></div>`;
                str+=`<input type="text" class="form-control cmtInput" value="${cvo.content}ffff"></li>`;           
            }
            ul.innerHTML+=str;
                     
            //댓글 페이징 코드
            let moreBtn=document.getElementById('moreBtn');
            console.log(moreBtn, result.pgvo.pageNo, result.endPage);
            //db에서 pgvo + list 같이 가져와야 값을 줄 수 있음
            if(result.pgvo.pageNo < result.endPage){
                moreBtn.style.visibility ='visible';//moreBtn표시
                moreBtn.dataset.page=page+1;        
            }else{
                moreBtn.style.visibility='hidden';//moreBtn숨김
            }
        }else{
            ul.innerText="댓글이 없습니다.";
        }
    })
} 

//수정 함수
async function cmtModify(cmtModData){
    try {
        const url = "/comment/cmtModify";
        const config={
                method : "put",
                headers : {
                    'content-type': 'application/json; charset=utf-8'
                },
                body : JSON.stringify(cmtModData)
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.loge(error)
    }
}
//삭제 함수
async function cmtRemove(cno){
    console.log("cmtRemove>>>");
    console.log("cno3>>>"+cno);
    try {
        const url ="/comment/cmtRemove/"+cno;
        const config={
            method : "delete"
        };
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error)
    }
}

//함수 호출하여 수정, 삭제, moreBtn
document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('modBtn')){ //수정
        let li=e.target.closest('li');
        let cmtCno = li.dataset.cno;
        let cmtContent=li.querySelector(".cmtInput").value;
        let cmtWriter = li.dataset.writer;
        // let cmtContent = li.querySelector(".form-control");
        let cmtModData={
            cno:cmtCno,
            writer:cmtWriter,
            content:cmtContent
        };
        cmtModify(cmtModData).then(result=>{
            if(result>0){
                alert("댓글 수정 성공");
            }else{
                alert("댓글 수정 실패")
            }
            commentListPrint(bnoVal);
        })
    }else if(e.target.classList.contains('delBtn')){ //삭제
        let li=e.target.closest('li');
        let cno=li.dataset.cno;
        console.log("cno>>>>"+cno);
        cmtRemove(cno).then(result=>{
            if(result>0){
                alert("댓글 삭제 성공")
            }else{
                alert("댓글 삭제 실패")
            }
            commentListPrint(bnoVal);
        })
    }else if(e.target.id=="moreBtn"){ //moreBtn
        commentListPrint(bnoVal,parseInt(e.target.dataset.page));
    }
})
//   <ul class="list-group" id="cmtListContainer">
//   <li class="list-group-item disabled" aria-disabled="true">
//   <div><span>사람 아이콘, writer, regAt</span>
//   <div>
//   <button type="button" id="cmtModBtn">수정</button>
//   <button type="button" id="cmtDelBtn">삭제</button>
//   </div></div>
//   <div><input type="text" value="${cvo.content}"></div></li>
//   </ul>