new Vue({
    el: '#index',
    data: {
        urgent_missions: [],
        search_missions:[],
        new_missions:[],
        users:[]
    },
    mounted() {
        let self = this;
        axios.defaults.baseURL = 'http://localhost:8080/'
        //获取紧急任务
        axios.get("api/MissionTable/orderMissionsByDdl", {
            params: {
                page: 1,
                size: 4
            }
        }).then(function (response) {
            self.urgent_missions = response.data.data;
        })
        //获取搜索的任务（默认先是全部）
        axios.get("api/MissionTable/getMissions_all", {
            params: {
                page: 1,
                size: 8
            }
        }).then(function (response) {
            self.search_missions = response.data.data;
        })
         //获取最新的任务
         axios.get("api/MissionTable/orderMissonsByTime", {
            params: {
                page: 1,
                size: 8
            }
        }).then(function (response) {
            self.new_missions = response.data.data;
        })
        //获取用户排行
        axios.get("api/user/rank", {
            params: {
                num: 10
            }
        }).then(function (response) {
            self.users = response.data;
            for(let i = 0; i < self.users.length; i++){
                self.users[i].username = self.plusXing(self.users[i].phone,3,4,'*');
            }
        })
    },
    methods:{
        goDetail:function(mission){
            window.location.href = "detail.html?mid="+mission.mid;
        },
        plusXing:function(str, frontLen, endLen, cha) {
            let len = str.length - frontLen - endLen;
            let xing = '';
            for (let i = 0; i < len; i++) {
                xing += cha;
            }
            return str.substring(0, frontLen) + xing + str.substring(str.length - endLen);
        }
    }
})