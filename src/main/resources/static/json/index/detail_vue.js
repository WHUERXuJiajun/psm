var urlinfo = window.location.href;
var len = urlinfo.length;
var offset = urlinfo.indexOf("?");
var newsidinfo = urlinfo.substr(offset, len)
var newsids = newsidinfo.split("=");
var newsid = newsids[1];//得到参数值
var newsname = newsids[0];
var mid = newsid;//取得任务id
new Vue({
    el: '#flyReply',
    data: {
        comments: [],
        reply_user:"",
        content:"",
    },
    mounted(){
        let self = this;
        //axios.defaults.baseURL = 'http://47.106.239.181:8080/'
        //获取评论
        axios.get("/api/comment/"+mid, {
        }).then(function (response) {
            self.comments = response.data;
            self.commentNum = self.comments.length;
        })

    },
    methods:{
        reply:function (comment) {
            let self = this;
            let _textarea = document.getElementById('L_content');
            _textarea.placeholder="回复 "+comment.from_user+" ：";
            self.reply_user = comment.from_user;
        },
        subReply:function () {
            console.log('11111111')
            let self = this;
            let token = window.localStorage.getItem('token');

            //axios.defaults.baseURL = 'http://47.106.239.181:8080/';

            axios.post("/api/comment/", {
                    "content": self.content,
                    "mid": mid,
                    "to_user": self.reply_user
                }, {
                    headers: {'Authorization': token}
                }
            ).then(function (response) {
                self.missions = response.data; //此时还是字符串
                alert('评论成功')
                console.log(self.phone)
                window.location.href="detail.html?mid="+mid;
            }).catch(function (error) {
                alert('评论失败')
            });
        },
        addThumb:function (comment) {
            let self = this;
            let token = window.localStorage.getItem('token');
            //axios.defaults.baseURL = 'http://47.106.239.181:8080/';
            axios.put("/api/comment/thumb", comment, {
                    headers: {'Authorization': token}
                }
            ).then(function (response) {
                self.comments = response.data; //此时还是字符串
                alert('点赞成功')
                window.location.href="detail.html?mid="+mid;
            }).catch(function (error) {
                alert('点赞失败')
            });
        }
    }
});