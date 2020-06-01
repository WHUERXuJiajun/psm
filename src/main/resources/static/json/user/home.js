var urlinfo = window.location.href;
var len = urlinfo.length;
var offset = urlinfo.indexOf("?");
var newsidinfo = urlinfo.substr(offset, len)
var newsids = newsidinfo.split("=");
var newsid = newsids[1];//得到参数值
var newsname = newsids[0];
var phone = newsid;//取得学号
new Vue({
    el: '#home',
    data: {
        users:[],
        post_missions:[],
        collect_missions:[]
    },
    mounted() {
        let self = this;
        let token = window.localStorage.getItem('token');
        //axios.defaults.baseURL = 'http://47.106.239.181:8080/'
        //获取用户信息
        axios.get("/api/user/phone", {
            params:{"phone":phone},
            headers: {'Authorization': token}
        }).then(function (response) {
            self.users.push(response.data);
        })

        axios.get("/api/post/getMissionsByPhone", {
            params:{"phone":phone},
            headers: {'Authorization': token}
        }).then(function (response) {
            self.post_missions = response.data; //此时还是字符串
        })

        axios.get("/api/collect", {
            params:{"phone": phone},
            headers: {'Authorization': token}
        }).then(function (response) {
            self.collect_missions = response.data; //此时还是字符串
        })
    },
    methods:{
        goDetail:function (message) {
            window.location.href = "../index/detail.html?mid="+message.mid;
        },
        goHome:function (message) {
            window.location.href = "home.html?phone="+message.phone;
        },
    }
})