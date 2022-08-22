function requestUserInfo () {
    const button = document.querySelector("#btn-user-info");
        button.addEventListener('click', () => {
            const nickname = document.querySelector("#nickname").value;
            if(nickname =='')
                alert('구단주를 입력하세요');
            else {
                fetch('/api/user/'+nickname)
                    .then((res) => res.json())
                    .then(data => {
                        console.log(data)
                        alert(JSON.stringify(data))
                    })
                    .catch(err => {
                        alert('error occurred')
                    });
            }
        } )



}
