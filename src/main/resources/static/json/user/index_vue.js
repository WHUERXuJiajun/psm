new Vue({
    el: '#index',
    data: {
        post_missions:[],
        collect_missions:[],
        accept_missions:[],
    },
    mounted() {
        let self = this;
        let token = window.localStorage.getItem('token');
        let phone = ""
        //axios.defaults.baseURL = 'http://47.106.239.181:8080/'

        axios.get("/api/user", {
            params:{},
            headers: {'Authorization': token}
        }).then(function (response) {
            phone = response.data; //此时还是字符串
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
        })



        //axios.defaults.baseURL = 'http://47.106.239.181:8080/'
        axios.get("/api/Rece", {
            params:{},
            headers: {'Authorization': token}
        }).then(function (response) {
            self.accept_missions = response.data; //此时还是字符串
        })
    },
    methods:{
        goDetail:function (mission) {
            window.location.href = "../index/detail.html?mid="+mission.mid;
        },

        cancelCollect:function (mission) {
            let self = this;
            let token = window.localStorage.getItem('token');
            //axios.defaults.baseURL = 'http://47.106.239.181:8080/'
            axios.delete("/api/collect", {
                data:mission,
                headers: {'Authorization': token}
            },).then(function (response) {
                alert('取消收藏成功')
                window.location.href="index.html"
            })
        },
        achieveMission:function (mission) {
            let self = this;
            let token = window.localStorage.getItem('token');
            //axios.defaults.baseURL = 'http://47.106.239.181:8080/'
            axios.get("/api/MissionTable/mission_cancel", {
                params:{"mid":mission.mid},
                headers: {'Authorization': token}
            },).then(function (response) {
                alert('此任务已经完成，对方信誉分+1，分数+1')
                window.location.href="index.html"
            })
        },
        cancelAccept:function (mission) {
            let self = this;
            let token = window.localStorage.getItem('token');
            //axios.defaults.baseURL = 'http://47.106.239.181:8080/'
            axios.delete("/api/Rece", {
                data:mission,
                headers: {'Authorization': token}
            },).then(function (response) {
                alert('取消接收成功')
                window.location.href="index.html"
            })
        },
        deadMission:function (mission) {
            let self = this;
            let token = window.localStorage.getItem('token');
            //axios.defaults.baseURL = 'http://47.106.239.181:8080/'
            axios.put("/api/user/updateCredit", mission,{
                headers: {'Authorization': token}
            },).then(function (response) {
                if(response.data == true){
                    alert('此任务失败，对方信誉分-2')
                    window.location.href="index.html"
                }else{
                    alert('未到截止时间！！！')
                }
            })
        }
    }
})