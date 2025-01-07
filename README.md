# locales-csr-v1

A method of locales by Client Side Rendering.
You need to add json file of transcript in your project.


settings:

    <body style="display:none"> // to hide raw data
    #translate{{__KEY__}}

    <script type="module">
    import {renderLocalesWithJson} from "PATH_OF_JS_FILE";
    ...
    renderLocalesWithJson({langCd, jsonFileName, displayTriggerComponent});