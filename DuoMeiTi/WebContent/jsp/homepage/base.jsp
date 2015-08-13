<%@ include file="/jsp/base/taglib.jsp" %>


<layout:override name="base_content">

<div id="nav-container">
  
    <div id="headerpic">
        <ul class="nav navbar-right">
            <li id="welcome_word">
                
            </li>
        </ul>

    </div>
   
        <div class="container-fluid main-container">
            <div class="row col-lg-12">
                <div class="col-md-offset-2 col-lg-8">
                    <div class="main-content mycontent">
                    	<layout:block name="main_content">
                    	</layout:block>                	
					</div>
				</div>
            </div>
        </div>
        {% block nav_content %}
        {% endblock %}
    </div>
</div>



</layout:override>










<%@ include file="/jsp/base/base.jsp" %>

