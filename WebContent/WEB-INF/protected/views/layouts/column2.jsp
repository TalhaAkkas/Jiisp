<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<div class="span-19">
	<div id="content">
		<jsp:include page='<%=(String)request.getAttribute("content")%>' />
	</div><!-- content -->
</div>
<div class="span-5 last">
	<div id="sidebar">
	<!-- <?php
		$this->beginWidget('zii.widgets.CPortlet', array(
			'title'=>'Operations',
		));
		$this->widget('zii.widgets.CMenu', array(
			'items'=>$this->menu,
			'htmlOptions'=>array('class'=>'operations'),
		));
		$this->endWidget();
	?> -->
	</div><!-- sidebar -->
</div>