// mysource.js
let xhtp = new XMLHttpRequest();
		xhtp.open('get', './board') //요청할 방식, 서버url
		xhtp.send(); // 서버요청, 실행
		xhtp.onload = function () { //요청결과 받아오면
			let data = JSON.parse(xhtp.response);
			console.log(data);
			let list = document.getElementById('list');
			// 서버의 json 파일 -> JSON.parse() -> 페이지 출력
			data.forEach(data => {
				let tr = makeTr(data);
				list.append(tr);
			})
		}

		function makeTr(data) {
			// tr 생성하는 부분
			 let tr = document.createElement('tr');
            
            let td0 = document.createElement('td');
            let chkbox = document.createElement('input');
            chkbox.setAttribute('type', 'checkbox');
            td0.append(chkbox);
            
           let txt1 = document.createTextNode(data.bno);
           let td1= document.createElement('td');
           td1.appendChild(txt1); 
        
          let td2 = document.createElement('td');
          let txt2 = document.createTextNode(data.title);
          td2.appendChild(txt2); 
        
           let td3 = document.createElement('td');
           let txt3 = document.createTextNode(data.content);
           td3.appendChild(txt3); 
        
           let td4 = document.createElement('td');
           let txt4 = document.createTextNode(data.writer);
           td4.appendChild(txt4); 
        
           let td5 = document.createElement('td');
           let txt5 = document.createTextNode(data.creation_date);
           td5.appendChild(txt5);
           
           let td6 = document.createElement('td');
           let btn = document.createElement('button');
           
            let txt6 = document.createTextNode('삭제');
           btn.append(txt6);
           td6.append(btn);
       

        tr.append(td0, td1, td2, td3, td4, td5, td6);

        return tr;
    }
			