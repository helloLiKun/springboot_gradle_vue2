/**
 * Created by Administrator on 2018/9/7.
 */
Vue.component('test-title',{
    props: ['title','message'],
    template:'<div>' +
    '<h3>{{title}}</h3>' +
    '<p>{{message}}</p>' +
    '</div>'
})
