const renderTranslation = (langCd, jsonFileName, displayTriggerComponent) => {
    let translations = {};
    const xhr = new XMLHttpRequest();
    xhr.open("GET", `/get-translation?langCd=${langCd}&pageNm=${jsonFileName}`, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            translations = JSON.parse(xhr.responseText);

            translateRecursive(document.querySelector("body"), translations, displayTriggerComponent);
            document.body.removeAttribute('style');
        }
    }
    xhr.send();
}
const translateRecursive = (node, langJson, displayTriggerComponent) => {
    // 상단부 번역처리가 완료되면 출력
    if (node.parentElement.classList.contains(displayTriggerComponent)) {
        document.body.removeAttribute('style');
    }

    if (node.nodeType === Node.TEXT_NODE) {
        const htmlHandler = document.createElement("div");
        const parentNode = node.parentNode;
        htmlHandler.innerHTML = node.nodeValue.replaceAll(/#translate{{\s*([^}]+?)\s*}}/g, (match, key) => translateWithJson(langJson, key));
        for(let child of htmlHandler.childNodes) {
            parentNode.insertBefore(child.cloneNode(true), node);
        }
        parentNode.removeChild(node);
    } else {
        for(let child of node.childNodes) {
            translateRecursive(child, langJson, displayTriggerComponent);
        }
    }
}
const translateWithJson = (langJson, key) => {
    if (langJson[key]) {
        return langJson[key];
    }
    return "";
}

export const renderLocalesWithJson = ({ langCd, jsonFileName, displayTriggerComponent }) => {
    renderTranslation(langCd, jsonFileName, displayTriggerComponent);
}