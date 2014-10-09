function checkStatus()
{
    var select_id = document.getElementById("test").value;
//    alert("Selected value is : " +select_id);
  //  document.write(select_id);
    $.ajax({
        url:  '/status',
        type: 'POST',
        dataType: 'json',
        data: {name: select_id}
    }).done(function(data){

        location.href="http://localhost:7777/";
    });
 }


function onClickStatus()
{
    User.saveStatus("testingnow", "1");
}