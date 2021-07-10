$.ajax({
    type: "post",
    url: "../../category/categoryServlet",
    dataType: 'json',
    success: function (result) {

        for (let i = 0; i < result.length; i++) {
            let element = result[i].cat_name;
            let template = `<li><a href="#">${element}<span>(258)</span></a></li>`;
            $("#category-data").append(template);

        }

    }
});

// $.ajax({
//     type: "post",
//     url: "../../category/categoryServlet",
//     dataType: 'json',
//     success: function (result) {
//             let element = result[0].cat_name;
//             $(".postone_cat").append(element);
//         }
// });


$.ajax({
    type: "post",
    url: "../../post/postServlet",
    dataType: 'json',
    success: function (result) {
        console.log(result);
        for (let i = ((result.length) - 1); i > 0; i--) {
            let template = ` <div class="postone">
            <a href="#">
                <div class="postone_top">
                    <div class="left_author_pic">
                        <img src="data:image/*;base64,${result[i].MEM_HEADSHOT}" width="50">
                    </div>

                    <div class="right_info">
                        <ul>
                            <li class="mem_author">${result[i].MEM_NAME}</li>
                            <li class="time">${result[i].POST_TIME}</li>
                        </ul>
                    </div>
                </div>

                <div class="postone_content">
                    <span class="postone_cat">${result[i].CAT_NAME}</span>
                    <span>// </span>

                    <span class="postone_title">${result[i].POST_TITLE}</span>
                    <div class="inner">
                        <div class="inner_text">
                            ${result[i].POST_CONTENT}
                        </div>
                    </div>
                    <div class="inner_count">

                        ‧ 回覆 <span> 10 </span>
                        ‧ 瀏覽 <span> 339 </span>
                    </div>
                </div>
            </a>
            <hr class="left1">
        </div>`;
            $("#post-container").append(template);

        }

    }
});



