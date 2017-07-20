/**
 * Created by hanlu on 2017/7/19.
 */

;(function($, window, document, undefined) {

    var defaults = {
        url : null,
        data : null
    };

    var logTemplate = {

        listLogTemplate :   '<tr>'
                              + '<td>##operationUserId##</td>'
                              + '<td>##operationUsername##</td>'
                              + '<td>##operationAddress##</td>'
                              + '<td>##operationParam##</td>'
                              + '<td>##operationDescription##</td>'
                              + '<td>##operationIp##</td>'
                              + '<td><fmt:formatDate value="##operationDate##" pattern="yyyy-MM-dd HH:mm:ss"/></td>'
                              + '</tr>',
        pagingTemplate : '<li><a href="#" ></a></li>'

    };

    _renderProjectData = function(projectData,index){
        if(projectData){
            var prjTemplateData = logTemplate.listLogTemplate;
            prjTemplateData = prjTemplateData.replace(/##operationUserId##/, projectData.operationUserId);
            prjTemplateData = prjTemplateData.replace(/##operationUsername##/, projectData.operationUsername);
            prjTemplateData = prjTemplateData.replace(/##operationAddress##/, projectData.operationAddress);
            prjTemplateData = prjTemplateData.replace(/##operationParam##/, projectData.operationParam);
            prjTemplateData = prjTemplateData.replace(/##operationDescription##/, projectData.operationDescription);
            prjTemplateData = prjTemplateData.replace(/##operationIp##/, projectData.operationIp);
            prjTemplateData = prjTemplateData.replace(/##operationDate##/, projectData.operationDate);
            return prjTemplateData;
        }
        return null;
    };
    _loadLogProjectData = function($container, myProjects, ajaxOption){
        $.ajax({
            type: "POST",
            url: ajaxOption.url,
            data:ajaxOption.data,
            dataType:'jsonp',
            jsonp:'callback',
            jsonpCallback:"successCallback",
            success: function(msg){
                var prjTemplate = "";
                for(var i=0;i<msg.length;i++){
                    prjTemplate += _renderProjectData(msg[i]);
                }
                $container.append(prjTemplate);
            }
        });
    };


    var methods = {
        init : function(options) {

            if (this && this.length > 1) {
                throw 'Container Element Must Be Unique!';
            }

            return this.each(function() {
                var $this = $(this), $container = $this;

                var plugin = {
                    '$container' : $container,
                    'settings' : null,
                    'myProjects' : []
                };

                plugin.settings = plugin.$container.data('logProject');

                plugin.settings = $.extend({}, defaults, options);

                var ajaxOption = {
                    'url' : options.url,
                    'data' : options.data
                }

                if (!ajaxOption.url) {
                    throw 'url can not be null!';
                }
                $container.html('');
                plugin.$container.data('logProject', plugin);

            });
        },

        showLogList : function(options) {

            if (this && this.length > 1) {
                throw 'Container Element Must Be Unique!';
            }

            return this.each(function() {
                var $this = $(this), $container = $this;

                var plugin = {
                    '$container' : $container,
                    'settings' : null,
                    'myProjects' : []
                };

                plugin.settings = plugin.$container.data('weatherProject');

                plugin.settings = $.extend({}, defaults, options);

                var ajaxOption = {
                    'url' : options.url,
                    'data' : options.data
                }

                if (!ajaxOption.url) {
                    throw 'url can not be null!';
                }
                $container.html('');
                _loadLogProjectData($container, plugin.myProjects,ajaxOption);
                plugin.$container.data('logProject', plugin);

            });
        },


        destroy : function(options) {
            return $(this).each(function() {
                var $this = $(this);
                $this.removeData('logProject');
            });
        }
    };


    $.fn.MyProject = function(option) {

        var method = arguments[0];

        if (methods[method]) {
            method = methods[method];
            arguments = Array.prototype.slice.call(arguments, 1);
        } else if (typeof (method) == 'object' || !method) {
            method = methods.init;
        } else {
            $.error('Method ' + method
                + ' does not exist on jQuery.logProject');
            return this;
        }

        return method.apply(this, arguments);

    };
})(jQuery, window, document);

