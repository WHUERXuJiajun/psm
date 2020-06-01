
//获取当前任务
new Vue({
    el: '#task',
    data: {
        missions: [],
        pageNum:1,
        label:"",
        money_missions:[]
    },
    mounted() {
        let self = this;
        //axios.defaults.baseURL = 'http://47.106.239.181:8080/'
        axios.get("/api/MissionTable/getMissions_label", {
            params: {
                label:"",
                page: 1,
                size: 8
            }
        }).then(function (response) {
            self.missions = response.data.data;
            self.pageNum = response.data.pageNum;
        })

        axios.get("/api/MissionTable/orderMissionsByMoney", {
            params: {
                page: 1,
                size: 8
            }
        }).then(function (response) {
            self.money_missions = response.data.data;
            console.log(self.money_missions)
        })
    },
    methods:{
        getMissionByLabel:function (label) {
            let self = this;
            self.label = label
            //axios.defaults.baseURL = 'http://47.106.239.181:8080/'
            axios.get("/api/MissionTable/getMissions_label", {
                params: {
                    label:self.label,
                    page: 1,
                    size: 8
                }
            }).then(function (response) {
                self.missions = response.data.data;
                self.pageNum = response.data.pageNum;
            })
        },
        goDetail:function (mission) {
            window.location.href = "detail.html?mid="+mission.mid;
        },
        selectPage:function (i) {
            let self = this;
            //axios.defaults.baseURL = 'http://47.106.239.181:8080/'
            axios.get("/api/MissionTable/getMissions_label", {
                params: {
                    label: self.label,
                    page: i,
                    size: 8
                }
            }).then(function (response) {
                self.missions = response.data.data;
                self.pageNum = response.data.pageNum;
            })
        }
    }
})