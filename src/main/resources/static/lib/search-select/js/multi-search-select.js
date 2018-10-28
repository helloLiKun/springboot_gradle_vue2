/**
 * Created by Administrator on 2018/9/24 0024.
 */
$("body").click(function(){
    hide($('.search-select-cls'));
});
function show(dom) {
    dom.css('display','block');
}
function hide(dom) {
    dom.css('display','none');
}
function  SearchSelect(data) {
    var liHeight=30;
    var self = this;
    self.checked=[];
    if (!data.id) {
        console.log('id不能为空');
        return;
    }
    if (!data.options) {
        console.log('选项为空')
    }
    var id = data.id;
    var showId=id+'-show-id';
    var optionIdPre="search_option_"+id+"_";
    var options = data.options;
    var divElement = $('#' + id);
    divElement.before("<div id='"+showId+"' class='show-cls'></div>");
    divElement.append("<input placeholder='输入关键字搜索'><div class='options-cls'><table></table></div>");
    var inputElement = $('#' + id + ' > input');
    var optionsElement=$('#'+id+' > .options-cls');
    var tabElement=$('#'+id+' > .options-cls > table');
    var trElement=$('#'+id+' > .options-cls > table > tr');
    var showElement=$('#'+showId);
    var width=showElement.width();
    showElement.click(function (e) {
        show(divElement);
        e.cancelBubble=true;
        e.stopPropagation();
        $('.search-select-cls').each(function () {
            var thisid=$(this).attr("id");
            if(thisid!=id){
               hide( $('#'+thisid))
            }
        })
    })

    inputElement.click(function (e) {
        e.stopPropagation();
    });

    inputElement.bind("input propertychange",function () {
        var val=inputElement.val();
        if(val!=null && val.trim()!=''){
            flushOptions(val.trim())
        }
    });

    var flushOptions=function (val) {
        var lisf=[];
        var lis=[];
        var simpleLis=[];
        for (var index = 0; index < options.length; index++) {
            var item=options[index];
            var id=item.val;
            var name=item.text;
            if(item!=null && item!='' && id!=null && name!=null){
                if(name.trim()==val){
                    simpleLis.push(item)
                }else {
                    if(name!=null && name!='' && id!=null &&id!=''){
                        var obj=new Object();
                        obj.val=id;
                        obj.text =name;
                        obj.matchs=name.split(val).length-1;
                        lis.push(obj);
                    }
                }
            }

        };
        if(lis.length>0){
            for(var i=0;i<lis.length-1;i++){
                for(var j=i+1;j<lis.length;j++){
                    var temp=lis[j];
                    if(lis[i].matchs>temp.matchs){
                        lis[j]=lis[i];
                        lis[i]=temp;
                    }
                }
            }
        };
        for(var i=0;i<simpleLis.length;i++){
            lisf.push(simpleLis[i])
        };
        for(var i=0;i<lis.length;i++){
            lisf.push(lis[lis.length-i-1])
        }
        if(lisf!=null && lisf.length>0){
            flushTrs(lisf)
        }
    };

    var flushTrs=function (opt) {
        tabElement.html('');
        tabElement.height(0);
        if(opt!=null && opt.length>0){
            for(var i=0;i<opt.length;i++){
                var option=opt[i];
                var optionId=optionIdPre+option.val;
                tabElement.append("<tr id='"+optionId+"'><td value='"+option.val+"'>"+option.text+"</td></tr>")
                var obj = {val:option.val,txt:option.text};
                $('#'+optionId).click(obj,trclick)
            }
            optionsElement.width(width);
            tabElement.width(width-2);
            trElement.width(width-2);
            var trNum=opt.length>5?5:opt.length;
            optionsElement.height(trNum*liHeight+15);
        }
    }

    function trclick(e) {
        e.stopPropagation();
        var val=e.data.val;
        var txt=e.data.txt;
        var btnId='btn-id-'+id+'-'+val;
        var have=false;
        for(var i=0;i<self.checked.length;i++){
            if(self.checked[i]==val){
                have=true;
            }
        }
        if(have){
            removeChecked(val,btnId)
        }else{
            addChecked(val,txt,btnId);
        }
    }

    function addChecked(val,txt,btnId) {
        $('#'+optionIdPre+val).css("background-color","whitesmoke")
        self.checked.push(val)
        var delId='del-id-'+id+'-'+val;
        var txtShow=txt;
        if(txt.length>5){
            txtShow=txt.substring(0,5);
        }
        showElement.append("<button type='button' class='btn btn-default btn-xs' id='"+btnId+"'>"+txtShow+"<span class='glyphicon glyphicon-remove' aria-hidden='true'  id="+delId+"></span></button>")
        $('#'+delId).click(function () {
            removeChecked(val,btnId);
        })
        checkedChange_();
    }

    function removeChecked(val,btnId) {
        $('#'+optionIdPre+val).css("background-color","white")
        self.checked.splice(self.checked.indexOf(val),1);;
        $('#'+btnId).remove();
        checkedChange_();
    }

    function initOptions() {
        flushTrs(options);
        for(var i=0;i<options.length;i++){
            if(options[i].checked){
                addChecked(options[i].val,options[i].text,'btn-id-'+id+'-'+options[i].val)
            }
        }
    }

    function checkedChange_() {
        if(self.checkedChange){
            self.checkedChange();
        };
    }
    initOptions();
}
