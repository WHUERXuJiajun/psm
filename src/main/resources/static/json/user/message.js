new Vue({
    el: '#message',
    data: {
        messages:[]
    },
    mounted() {
        let self = this;
        let token = window.localStorage.getItem('token');
        //axios.defaults.baseURL = 'http://47.106.239.181:8080/'
        //获取消息
        axios.get("/api/comment/user", {
            params:{},
            headers: {'Authorization': token}
        }).then(function (response) {
            self.messages = response.data;
        })
    },
    methods:{
        goDetail:function (message) {
            window.location.href = "../index/detail.html?mid="+message.mid;
        },
        goHome:function () {
            //获取用户信息
            let token = window.localStorage.getItem('token');

            axios.get("/api/user", {
                params:{},
                headers: {'Authorization': token}
            }).then(function (response) {
                window.location.href = "home.html?phone="+response.data;
            })

        },
    }
})