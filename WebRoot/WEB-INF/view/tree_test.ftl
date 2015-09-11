<head>
	<script src="/Nirvana/static/js/jquery.js" type="text/javascript"></script>
	<script src='/Nirvana/static/js/dynatree-master/jquery/jquery-ui.custom.js' type="text/javascript"></script>
    <script src='/Nirvana/static/js/dynatree-master/jquery/jquery.cookie.js' type="text/javascript"></script>
    <link rel='stylesheet' type='text/css' href='/Nirvana/static/js/dynatree-master/dist/skin/ui.dynatree.css'>
   <script src='/Nirvana/static/js/dynatree-master/dist/jquery.dynatree.js' type="text/javascript"></script>
</head>
<body>
<div id=tree>
</div>
</body>

<script>
	$("#tree").dynatree({
	 children: [
   				{title: "全部职位", key: "all-0","isLazy": true},
   			],
   	checkbox:true,	
    onLazyRead: function(node){
    	var type=node.data.key.split('-')[0];
    	var url='';
    	var res=[];
    	switch  (type)
    	{
    	case 'all':
    		url='/Nirvana/maindata/api/bigareas';
    		 res.push({title: "" + "GM" });
    		 res.push({title: "" + "资讯" });
    		 res.push({title: "" + "SISM"});
    		 res.push({title: "" + "MDM" });
    		 res.push({title: "" + "CDM" });
    		 res.push({title: "" + "SIS" });
    		break;
    	case 'bigArea':
    		 res.push({title: "" + "UM" });
    		  res.push({title: "" + "FSIS" });
    		 
    		url='/Nirvana/maindata/api/bigareas/{id}/managerareas';
    		break;
    	case 'managerArea':
    		 res.push({title: "" + "TDM" });
    		 res.push({title: "" + "文员" });
    		url='/Nirvana/maindata/api/managerarea/{id}/directorareas';
    		break;
    	case 'directorArea':
    	 	res.push({title: "" + "TDS" });
    		url='/Nirvana/maindata/api/directorarea/{id}/agentareas';
    		break;		  					
    	}
    	
    	url=url.replace('{id}',node.data.key.split('-')[1]);
        node.appendAjax({url: url,
        				 data:{unselectable: true,hideCheckbox:true}
                          });
       
    		node.addChild(res);                  
    }
});


</script>